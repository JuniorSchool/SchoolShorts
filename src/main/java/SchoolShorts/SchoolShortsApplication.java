package SchoolShorts;

import jakarta.servlet.DispatcherType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.filter.ForwardedHeaderFilter;

@SpringBootApplication
public class SchoolShortsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolShortsApplication.class, args);
	}

        @Bean
        public ServletRegistrationBean countingTablesServletBean() {
            ServletRegistrationBean bean = new ServletRegistrationBean(
              new ServletPDFCountingTable(), "/ServletPDFCountingTable");
            bean.setLoadOnStartup(1);
            return bean;
        }        
        
/*
        /// Issue Reference link: https://github.com/spring-projects/spring-boot/issues/27801
        /// server.tomcat.use-relative-redirects=true was not being honored
        @Bean
        @ConditionalOnProperty(value = "server.forward-headers-strategy", havingValue = "framework")
        FilterRegistrationBean<ForwardedHeaderFilter> customFilter() {
            ForwardedHeaderFilter filter = new ForwardedHeaderFilter();
            filter.setRelativeRedirects(true);
            FilterRegistrationBean<ForwardedHeaderFilter> registration = new FilterRegistrationBean<>(filter);
            registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.ERROR);
            registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
            return registration;
        } 
*/
        
}
