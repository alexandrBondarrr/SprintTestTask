package com.testTask.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

   @Override
   protected Class<?>[] getRootConfigClasses() {
      return new Class[] { HibernateConfig.class };
   }

   @Override
   protected Class<?>[] getServletConfigClasses() {
      return new Class[] { WebMvcConfig.class };
   }

   @Override
   protected String[] getServletMappings() {
      return new String[] { "/" };
   }

   @Override
   public void onStartup(ServletContext servletContext) throws ServletException {

      EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE);

      FilterRegistration.Dynamic characterEncoder = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
      characterEncoder.setInitParameter("encoding", "UTF-8");
      characterEncoder.setInitParameter("forceEncoding", "true");
      characterEncoder.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
      characterEncoder.setAsyncSupported(true);

      super.onStartup(servletContext);
   }
}
