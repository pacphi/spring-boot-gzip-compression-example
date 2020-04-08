package io.pivotal.gzip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ConfigurationPropertiesScan
public class App {

	@Configuration
	static class TraceConfig {

		@Bean
		HttpTraceRepository httpTraceRepository() {
			return new InMemoryHttpTraceRepository();
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}