package my.spotit.asset.report;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.sql.DataSource;

import static my.spotit.asset.DexConstants.REPORT_PATH;

@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public void generate(String reportName, OutputStream outputStream, Map<String, Object> params) {
        try {
            File file = loadReport(reportName);
            JasperReport jasperReport = compileOrLoad(file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (Exception e) {
            log.error("Fail to generate report {}", reportName);
            e.printStackTrace();
        }
    }

    private String[] getSourceExtension() {
        return new String[]{"jasper", "jrxml"};
    }

    private File loadReport(String reportName) {
        return loadReportSource(reportName);
    }

    private JasperReport compileOrLoad(File file) throws Exception {
        if (null == file) throw new ReportException("Input file should not null");
        return (file.getName().endsWith("jasper")) ?
                (JasperReport) JRLoader.loadObject(new FileInputStream(file))
                :
                JasperCompileManager.compileReport(new FileInputStream(file));
    }


    private File searchReportSource(String reportName) {
        try {
            log.debug("report name: " + reportName);
            File file = findFileInPath(REPORT_PATH, reportName);
            if (null != file) return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static File loadReportSource(final String reportName) {
        String path = REPORT_PATH + File.separator + reportName;
        try {
            ClassPathResource resource = new ClassPathResource(path);
            if (resource.exists())
                return resource.getFile();
            else
                return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File findFileInPath(String path, String name) {
        String reportClassPath = getReportClassPath(path);
        log.debug("report name: " + reportClassPath);
        for (String extension : getSourceExtension()) {
            File file = findFileInPath(reportClassPath, name, extension);
            if (null != file) return file;
        }
        return null;
    }

    private String getReportClassPath(String path) {
        ClassPathResource resource = new ClassPathResource(path);
        try {
            return resource.getURI().getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File findFileInPath(String path, String name, String ext) {
        File f = new File(path, name + "." + ext);
        if (f.exists()) {
            return f;
        } else {
            return null;
        }
    }

}
