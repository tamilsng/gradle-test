package com.aub.docs.config.swagger;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * <pre>
 *   Periodically poll the service instaces and update the in memory store as key value pair
 * </pre>
 */
@Component
public class ServiceDescriptionUpdater {

	private static final Logger logger = LogManager.getLogger(ServiceDescriptionUpdater.class);

	@Autowired
	private SwaggerServiceProperties swaggerServiceProperties;

	private final RestTemplate template;

	public ServiceDescriptionUpdater() {
		this.template = new RestTemplate();
	}

	@Autowired
	private ServiceDefinitionsContext definitionContext;

	 @PostConstruct
	public void refreshSwaggerConfigurations() {
		logger.debug("Starting Service Definition Context refresh");

		swaggerServiceProperties.getServices().forEach(service -> {
			String serviceId = service.getName();
			String serviceurl = service.getUrl();
			logger.info(" swagger service" + serviceId);
			logger.info("serviceurl" + serviceurl);
			Optional<Object> jsonData = getSwaggerDefinitionForAPI(serviceId, serviceurl);
			if (jsonData.isPresent()) {
				String content = getJSON(serviceId, jsonData.get());
				definitionContext.addServiceDefinition(serviceId, addProperties(content, service));
			}
		});

	}

	/**
	 * To add host and basepath properties
	 * 
	 * @param json
	 * @param service
	 * @return
	 */
	String addProperties(String json, SwaggerService service) {
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(json);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		jsonObject.addProperty("host", service.getHost());
		jsonObject.addProperty("basePath", service.getBasePath());
		return jsonObject.toString();
	}

	private Optional<Object> getSwaggerDefinitionForAPI(String serviceName, String url) {
		logger.debug("Accessing the SwaggerDefinition JSON for Service : {} : URL : {} ", serviceName, url);
		try {
			Object jsonData = template.getForObject(url, Object.class);
			return Optional.of(jsonData);
		} catch (RestClientException ex) {
			logger.error("Error while getting service definition for service : {} Error : {} ", serviceName,
					ex.getMessage());
			return Optional.empty();
		}

	}

	public String getJSON(String serviceId, Object jsonData) {
		try {
			String s = new ObjectMapper().writeValueAsString(jsonData).toString();
			s = s.replace("{\"\":\"{", "{");
			s = s.replace("}}}\"}", "}}}");
			s = s.replace("\\\"", "\"");
			return s;

		} catch (JsonProcessingException e) {
			logger.error("Error : {} ", e.getMessage());
			return "";
		}
	}
}
