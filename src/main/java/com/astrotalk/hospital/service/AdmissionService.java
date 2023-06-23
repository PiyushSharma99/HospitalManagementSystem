package com.astrotalk.hospital.service;

import java.util.List;
import java.util.Optional;

import com.astrotalk.hospital.model.Admission;


public interface AdmissionService {
	
	Admission save(Admission admission);
	void deleteById(Long admissionId);
	Optional<Admission> findById(Long admissionId);
	List <Admission> findAll();
	List <Admission> findByPatientId(Long patientId);
	

}
