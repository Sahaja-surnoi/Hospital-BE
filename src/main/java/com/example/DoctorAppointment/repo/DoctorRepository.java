package com.example.DoctorAppointment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DoctorAppointment.model.DoctorAppointment;

public interface DoctorRepository extends JpaRepository<DoctorAppointment, Long> {

}
