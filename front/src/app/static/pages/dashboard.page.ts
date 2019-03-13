import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from 'rxjs/index';
import {BreadcrumbService} from "../../breadcrumb.service";
import {environment} from "../../../environments/environment";

@Component({
    selector: 'cng-dashboard',
    templateUrl: './dashboard.page.html'
})
export class DashboardPage implements OnInit {

    DASHBOARD_API: string = environment.endpoint + '/api/dashboard';
    currentUser$: Observable<any>;

    countWorkOrder: number= 1;
    countMaintenanceRequest: number = 2;
    countStaff: number = 3;
    countAsset: number = 4;
    lineData: any;
    barData: any;
    timeData: any;
    changedBarData: any;
    changedTimeData: any;
    pieData: any;
    polarData: any;
    radarData: any;

    breadcrumbs: any [] = [
        {label: 'Dashboard'},
    ];

    constructor(public http: HttpClient,
                public breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems(this.breadcrumbs);

        this.barData = {
            labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4', 'Week 5'],
            datasets: [{
                label: 'Weekly Work Order',
                backgroundColor: '#2162b0',
                borderColor: '#2162b0',
                data: []
            }
            ]
        };

        this.timeData = {
            labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4', 'Week 5'],
            datasets: [{
                label: 'Work Order Time Spent',
                backgroundColor: '#2162b0',
                borderColor: '#2162b0',
                data: []
            }
            ]
        };
    }

    ngOnInit() {
        this.lineData = {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [
                {
                    label: 'First Dataset',
                    data: [65, 59, 80, 81, 56, 55, 40],
                    fill: false,
                    borderColor: '#2162b0'
                },
                {
                    label: 'Second Dataset',
                    data: [28, 48, 40, 19, 86, 27, 90],
                    fill: false,
                    borderColor: '#e02365'
                }
            ]
        };


        this.pieData = {
            labels: ['A', 'B', 'C'],
            datasets: [
                {
                    data: [300, 50, 100],
                    backgroundColor: [
                        '#2162b0',
                        '#e02365',
                        '#eeb210'
                    ]
                }]
        };

        this.polarData = {
            datasets: [{
                data: [
                    11,
                    16,
                    7,
                    3,
                    14
                ],
                backgroundColor: [
                    '#2162b0',
                    '#e02365',
                    '#eeb210',
                    '#17AFC2',
                    '#AB44BC'
                ],
                label: 'My dataset'
            }],
            labels: [
                'Red',
                'Green',
                'Yellow',
                'Grey',
                'Blue'
            ]
        };

        this.radarData = {
            labels: ['Eating', 'Drinking', 'Sleeping', 'Designing', 'Coding', 'Cycling', 'Running'],
            datasets: [
                {
                    label: 'My First dataset',
                    backgroundColor: 'rgba(179,181,198,0.2)',
                    borderColor: 'rgba(179,181,198,1)',
                    pointBackgroundColor: 'rgba(179,181,198,1)',
                    pointBorderColor: '#fff',
                    pointHoverBackgroundColor: '#fff',
                    pointHoverBorderColor: 'rgba(179,181,198,1)',
                    data: [65, 59, 90, 81, 56, 55, 40]
                },
                {
                    label: 'My Second dataset',
                    backgroundColor: 'rgba(255,99,132,0.2)',
                    borderColor: 'rgba(255,99,132,1)',
                    pointBackgroundColor: 'rgba(255,99,132,1)',
                    pointBorderColor: '#fff',
                    pointHoverBackgroundColor: '#fff',
                    pointHoverBorderColor: 'rgba(255,99,132,1)',
                    data: [28, 48, 40, 19, 96, 27, 100]
                }
            ]
        };

        this.changeData();
    }

    changeData() {
        let data = [];
        this.http.get(this.DASHBOARD_API + '/work-order-weekly-projections')
            .subscribe((projection: any[]) => {
                projection.forEach(p => {
                    data.push(p.count);
                });

                this.changedBarData = {
                    labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4', 'Week 5'],
                    datasets: [{
                        label: 'Weekly Work Order',
                        backgroundColor: '#2162b0',
                        borderColor: '#2162b0',
                        data: data
                    }
                    ]
                };
                this.barData = Object.assign({}, this.changedBarData);
            });

        let tdata = [];
        this.http.get(this.DASHBOARD_API + '/work-order-weekly-time-spent-projections')
            .subscribe((projection: any[]) => {
                projection.forEach(p => {
                    tdata.push(p.total);
                });

                this.changedTimeData = {
                    labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4', 'Week 5'],
                    datasets: [{
                        label: 'Time Spent Weekly',
                        backgroundColor: '#2162b0',
                        borderColor: '#2162b0',
                        data: tdata
                    }
                    ]
                };
                this.timeData = Object.assign({}, this.changedTimeData);
            });

        this.http.get<number>(this.DASHBOARD_API + '/work-order-count')
            .subscribe(count => this.countWorkOrder = count);

        this.http.get<number>(this.DASHBOARD_API + '/maintenance-request-count')
            .subscribe(count => this.countMaintenanceRequest = count);

        this.http.get<number>(this.DASHBOARD_API + '/staff-count')
            .subscribe(count => this.countStaff = count);

        this.http.get<number>(this.DASHBOARD_API + '/asset-count')
            .subscribe(count => this.countAsset = count);

    }
}
