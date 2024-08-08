package com.example.DoctorAppointment.controller;

import java.util.List;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DoctorAppointment.model.DoctorAppointment;
import com.example.DoctorAppointment.repo.DoctorRepository;



@RestController
@RequestMapping("/api/HospitalAppointment")
public class AppointmentController {
	
	
	@Autowired
	DoctorRepository doctorRepository;

	private Counter requestCounter;

	@Autowired
	public void MetricsController(MeterRegistry meterRegistry) {
		this.requestCounter = Counter.builder("api_requests_total")
				.description("Total number of API requests")
				.register(meterRegistry);
	}
	
	@PostMapping("/AddNewAppointment")
	public ResponseEntity<DoctorAppointment> bookAppointment(@RequestBody DoctorAppointment doctorAppointment){
	return new ResponseEntity<>(doctorRepository.save(doctorAppointment), HttpStatus.CREATED);
	
	}
	
	@GetMapping("/getAppointments")
	public ResponseEntity<List<DoctorAppointment>> getAppointments(){
	return new ResponseEntity<>(doctorRepository.findAll(), HttpStatus.OK);
	
	}
	@GetMapping("/exampleEndpoint")
	public String exampleEndpoint() {
		requestCounter.increment();  // Increment counter for each request
		return "Endpoint hit count: " + requestCounter.count();
	}

}
