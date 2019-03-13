CREATE OR REPLACE VIEW DEX_WORK_ORDR_WEEK_PRJN AS
  WITH weeks AS (
    SELECT
      greatest(date_trunc('week', dates.d), date_trunc('month', dates.d))         weekstart,
      least(date_trunc('week', dates.d) + INTERVAL '6 day',
            date_trunc('month', dates.d) + INTERVAL '1 month' - INTERVAL '1 day') weekend
    FROM generate_series(date_trunc('month', current_date),
                         date_trunc('month', now()) + INTERVAL '1 month' - INTERVAL '1 day', '1 day') AS dates(d)
    GROUP BY 1, 2
    )
    SELECT
      w.weekstart                                            weekstart,
      w.weekend                                              weekend,
      ((date_part('day', w.weekend) :: INTEGER - 1) / 6) + 1 week,
      count(coalesce(o.id, 0))                         total
    FROM weeks w LEFT JOIN dex_work_ordr o ON o.c_ts BETWEEN w.weekstart AND w.weekend
                 LEFT JOIN dex_work_ordr_log l on l.work_order_id = o.id

    GROUP BY w.weekstart, w.weekend
    ORDER BY w.weekend;

--- WORK ORDER TIME SPENT
CREATE OR REPLACE VIEW DEX_WORK_ORDR_WEEK_TIME_SPNT_PRJN AS
  WITH weeks AS (
    SELECT
      greatest(date_trunc('week', dates.d), date_trunc('month', dates.d))         weekstart,
      least(date_trunc('week', dates.d) + INTERVAL '6 day',
            date_trunc('month', dates.d) + INTERVAL '1 month' - INTERVAL '1 day') weekend
    FROM generate_series(date_trunc('month', current_date),
                         date_trunc('month', now()) + INTERVAL '1 month' - INTERVAL '1 day', '1 day') AS dates(d)
    GROUP BY 1, 2
    )

    SELECT
      w.weekstart                                            weekstart,
      w.weekend                                              weekend,
      ((date_part('day', w.weekend) :: INTEGER - 1) / 6) + 1 week,
      coalesce(EXTRACT(EPOCH FROM sum(l.stop_time - l.start_time))/3600, 0)  total
    FROM weeks w LEFT JOIN dex_work_ordr o ON o.c_ts BETWEEN w.weekstart AND w.weekend
    GROUP BY w.weekstart, w.weekend
    ORDER BY w.weekend;



-- select wo.id, wo.reference_no, sum(l.stop_time - l.start_time) as total FROM dex_work_ordr_log l
-- inner join dex_work_ordr wo on wo.id = l.work_order_id
-- group by wo.id