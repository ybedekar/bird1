package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "account")
@EntityScan("account.entity")  
@EnableJpaRepositories("account.repository")
public class Application extends SpringBootServletInitializer{
	public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
     
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
            	
            	container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/http500Error"));
                container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/http400Error"));
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/http404Error"));
                container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/http403Error"));
            }
        };
    }
}


