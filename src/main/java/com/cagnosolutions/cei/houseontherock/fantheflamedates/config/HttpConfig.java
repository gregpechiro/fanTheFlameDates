package com.cagnosolutions.cei.houseontherock.fantheflamedates.config;

import freemarker.template.TemplateException;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;

@Configuration
public class HttpConfig extends WebMvcConfigurerAdapter {

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

    @Bean
    public FreeMarkerConfigurer freeMarkerConfig() throws IOException, TemplateException {
        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
        factory.setTemplateLoaderPath("/templates/");
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        result.setConfiguration(factory.createConfiguration());
        return result;
    }

    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver view = new FreeMarkerViewResolver();
        view.setPrefix("");
        view.setSuffix(".html");
        view.setCache(false);
        view.setOrder(1);
        view.setExposeSpringMacroHelpers(true);
        view.setExposeRequestAttributes(true);
        view.setExposeSessionAttributes(true);
        view.setViewNames(new String[]{"*"});
        return view;
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            ErrorPage error401 = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
            ErrorPage error403 = new ErrorPage(HttpStatus.FORBIDDEN, "/403.html");
            ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            ErrorPage error405 = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/405.html");
            ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
            container.addErrorPages(error401, error403, error404, error405, error500);
        };
    }

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("4MB");
		factory.setMaxRequestSize("4MB");
		return factory.createMultipartConfig();
	}
}
