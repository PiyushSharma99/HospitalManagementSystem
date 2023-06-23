package com.astrotalk.hospital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astrotalk.hospital.model.Admission;
import com.astrotalk.hospital.repository.AdmissionRepository;


@Service
public class AdmissionServiceImpl implements  AdmissionService{

	
	@Autowired
    private AdmissionRepository admissionRepository;
	
	@Override
	public Admission save(Admission admission) {
		// TODO Auto-generated method stub
		return admissionRepository.save(admission);
	}

	@Override
	public void deleteById(Long admissionId) {
		// TODO Auto-generated method stub
		admissionRepository.deleteById(admissionId);
		
	}

	@Override
	public Optional<Admission> findById(Long admissionId) {
		// TODO Auto-generated method stub
		return admissionRepository.findById(admissionId);
	}

	@Override
	public List<Admission> findAll() {
		// TODO Auto-generated method stub
		return admissionRepository.findAll();
	}

	@Override
	public List<Admission> findByPatientId(Long patientId) {
		// TODO Auto-generated method stub
		return admissionRepository.findByPatientId(patientId);
	}




}
