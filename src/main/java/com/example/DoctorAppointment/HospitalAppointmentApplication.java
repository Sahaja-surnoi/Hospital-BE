package com.example.DoctorAppointment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class HospitalAppointmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalAppointmentApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer mvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://hospital-fe.s3-website-ap-southeast-1.amazonaws.com") // Frontend URL
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Ensure OPTIONS is included for preflight requests
						.allowedHeaders("*") // Allows all headers
						.allowCredentials(true); // Allows credentials if needed
			}
		};
	}
}
