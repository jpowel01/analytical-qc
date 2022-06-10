package gov.epa.analyticalqc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnalyticalQcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyticalQcApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/analytical-qc-javaconfig").allowedOrigins("http://localhost:81");
			}
		};
	}

}
