package com.astrotalk.hospital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astrotalk.hospital.model.Patient;
import com.astrotalk.hospital.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService{

	
	@Autowired
    private PatientRepository patientRepository;
	
	@Override
	public Patient save(Patient patient) {
		// TODO Auto-generated method stub
		return patientRepository.save(patient);
	}

	

	
	@Override
	public List<Patient> findAll() {
		// TODO Auto-generated method stub
		
		
		
		return patientRepository.findAll();
		
		
	}




	@Override
	public void deleteById(Long patientId) {
		// TODO Auto-generated method stub
		patientRepository.deleteById(patientId);
		
	}




	@Override
	public Optional<Patient> findById(Long patientId) {
		// TODO Auto-generated method stub
		return patientRepository.findById(patientId);
	}




	@Override
	public List<Patient> findAdmittedPatients(String status) {
		// TODO Auto-generated method stub
		return patientRepository.findAdmittedPatients(status);
	}



	@Override
	public void dischargePatient(String status, Long patient_id) {
		// TODO Auto-generated method stub
		patientRepository.dischargePatient(status, patient_id);
	}





	

}
