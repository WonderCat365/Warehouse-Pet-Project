package com.wondercat.testapp.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class AppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws
            ServletException {

        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();
        context.register(AppConfiguration.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);

        ServletRegistration.Dynamic servletRegistration = servletContext.
                addServlet("dispatcher", dispatcherServlet);

        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");
    }
}

//public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[]{AppConfiguration.class};
//    }
//
//    protected Class<?>[] getServletConfigClasses() {
//        return null;
//    }
//
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//}
