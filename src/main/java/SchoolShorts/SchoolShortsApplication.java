package SchoolShorts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

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
        
}
