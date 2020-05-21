package com.aub.docs.config.swagger;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("api")
public class SwaggerServiceProperties {

	private List<SwaggerService> services;

	public List<SwaggerService> getServices() {
		return services;
	}

	public void setServices(List<SwaggerService> services) {
		this.services = services;
	}
	


}
