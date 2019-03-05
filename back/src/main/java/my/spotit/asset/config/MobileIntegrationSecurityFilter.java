package my.spotit.asset.config;

import my.spotit.asset.integration.mobile.security.MobileSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MobileIntegrationSecurityFilter extends GenericFilterBean {

    private static final Logger LOG = LoggerFactory.getLogger(MobileIntegrationSecurityFilter.class);

    private MobileSecurityService mobileSecurityService;

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        if (mobileSecurityService == null) {
            ServletContext servletContext = req.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            mobileSecurityService = webApplicationContext.getBean(MobileSecurityService.class);
        }

        String username = request.getParameter("username");
        String deviceId = request.getParameter("deviceId");

        LOG.debug("username {}, deviceId {} ", username, deviceId);

        try {
            mobileSecurityService.authenticate(username, deviceId);
        } catch (Exception e) {
            LOG.debug(e.getMessage());
            e.printStackTrace();
        }
        chain.doFilter(req, res);
    }
}
