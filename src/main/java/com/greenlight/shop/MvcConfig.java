package com.greenlight.shop;

import java.io.File;
import java.nio.file.FileSystems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.EncodedResourceResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Autowired
	RequestServiceInterceptor requestInterceptor;
	
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/v1/**").allowedMethods("*").allowedOrigins("*").allowCredentials(true);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String userDirectory = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
		String fileDirectory = userDirectory + "/items";
		File file = new File(fileDirectory);
		if (!file.exists()) {
			file.mkdir();
		}
		registry.addResourceHandler("/css/*").addResourceLocations("classpath:/static/css/").setCachePeriod(5*60).resourceChain(true).addResolver(new EncodedResourceResolver());
		registry.addResourceHandler("/js/*").addResourceLocations("classpath:/static/js/").setCachePeriod(5*60).resourceChain(true).addResolver(new EncodedResourceResolver());;
		registry.addResourceHandler("/*").addResourceLocations("classpath:/static/").resourceChain(true).addResolver(new EncodedResourceResolver());;
		registry.addResourceHandler("/images").addResourceLocations("classpath:/static/images/").resourceChain(true).addResolver(new EncodedResourceResolver());
		registry.addResourceHandler("/fonts").addResourceLocations("classpath:/static/fonts/").setCachePeriod(5*60).resourceChain(true).addResolver(new EncodedResourceResolver());
				
		registry.addResourceHandler("/img/*", "/api/v1/img/*").addResourceLocations("classpath:/static/img/").setCachePeriod(3600).resourceChain(true).addResolver(new EncodedResourceResolver());
		System.out.println(fileDirectory);
		// user file:/// in ubuntu os
		registry.addResourceHandler("/items/*").addResourceLocations("file:///" + fileDirectory + "/").setCachePeriod(3600)
		.resourceChain(true).addResolver(new EncodedResourceResolver());
		registry.addResourceHandler("/api/v1/items/*").addResourceLocations("file:///" + fileDirectory + "/").setCachePeriod(3600)
		.resourceChain(true).addResolver(new EncodedResourceResolver());
	}

}
