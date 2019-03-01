package my.spotit.asset.report;

import java.io.OutputStream;
import java.util.Map;

public interface ReportService {

    void generate(String name, OutputStream outputStream, Map<String, Object> params);

}
