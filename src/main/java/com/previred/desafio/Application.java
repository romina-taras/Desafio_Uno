package com.previred.desafio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.previred.desafio.model.Periodo;

/**
 * Clase principal, donde comienza la ejecución.
 * 
 * @author romina-taras
 */
@SpringBootApplication
public class Application {

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);
	private static final String SERVICE_URL = "http://127.0.0.1:8080/periodos/api";

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			LOG.info("Iniciando conexion al servicio REST...");
			Periodo periodo = restTemplate.getForObject(SERVICE_URL, Periodo.class);
			LOG.info(periodo.toString());
			LOG.info("Iniciando procesamiento de datos...");
			ApplicationUtils.processPeriodo(periodo);
			LOG.info("Terminando la ejecucion...");
		};
	}
}
