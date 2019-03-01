package my.spotit.asset.report;

import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */
public class JasperServlet extends HttpServlet {

    public static final String PARAM_REPORT = "report";
    private final Logger LOG = LoggerFactory.getLogger(JasperServlet.class);

    @Autowired
    private ReportService reportService;

    private ServletContext servletContext;

    private JRPdfExporter exporter;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        servletContext = config.getServletContext();
        exporter = new JRPdfExporter();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        try {
            // transform parameters from URL
            Map<String, Object> map = new HashMap<String, Object>();
            Enumeration parameterNames = req.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = (String) parameterNames.nextElement();
                LOG.debug(paramName + ": " + req.getParameter(paramName));
                map.put(paramName, req.getParameter(paramName));
            }

            String reportName = req.getParameter(PARAM_REPORT);
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename=" + reportName + ".pdf");
            reportService.generate(reportName, response.getOutputStream(), map);
        } catch (ReportException e) {
            e.printStackTrace();
        } finally {
            exporter.reset();
        }
    }
}