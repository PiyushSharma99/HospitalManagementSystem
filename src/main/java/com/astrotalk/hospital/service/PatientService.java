	package com.astrotalk.hospital.service;

import java.util.List;
import java.util.Optional;

import com.astrotalk.hospital.model.Patient;



public interface PatientService {
	
	
		Patient save(Patient patient);
		
		void deleteById(Long patientId);
		
		Optional<Patient> findById(Long patientId);
		
		List <Patient> findAll();
		
		List<Patient> findAdmittedPatients(String status);
	
		void dischargePatient(String status, Long patient_id);

}
