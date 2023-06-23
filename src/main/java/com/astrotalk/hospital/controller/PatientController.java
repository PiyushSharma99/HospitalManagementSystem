package com.astrotalk.hospital.controller;




import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.astrotalk.hospital.constants.Constant;


import com.astrotalk.hospital.model.Patient;

import com.astrotalk.hospital.response.ResponseHandler;

import com.astrotalk.hospital.service.PatientService;

@RestController
@RequestMapping("/hospital/users")
public class PatientController {
	@Autowired
	private PatientService patientService;
	

	@GetMapping("/patients")
	public ResponseEntity<Object> getAllPatients() {
		try {
			List<Patient> patients = patientService.findAll();
			if (patients.isEmpty() || patients.size() == 0) {
				return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.NO_CONTENT, null);
			}
			return ResponseHandler.generateResponse(Constant.SUCCESS_GET_MESSAGE, HttpStatus.OK, patients);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	@GetMapping("/patients/admit")
	public ResponseEntity<Object> getAllAdmittedPatients() {
		try {
			List<Patient> patients = patientService.findAdmittedPatients(Constant.ADMITTED);
			if (patients.isEmpty() || patients.size() == 0) {
				return ResponseHandler.generateResponse(Constant.NO_ADMITTED_PATIENT_MESSAGE, HttpStatus.OK, null);
			}
			return ResponseHandler.generateResponse(Constant.SUCCESS_GET_MESSAGE, HttpStatus.OK, patients);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@GetMapping("/patients/{patientId}")
	public ResponseEntity<Object> getPatient(@PathVariable Long patientId) {
		try {
			Optional<Patient> searchedPatient = patientService.findById(patientId);
			if (searchedPatient.isEmpty()) {
				return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.NO_CONTENT, null);
			}
			return ResponseHandler.generateResponse(Constant.SUCCESS_GET_MESSAGE, HttpStatus.OK, searchedPatient.get());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@PostMapping("/patients")
	public ResponseEntity<Object> createPatient(@RequestBody Patient patient) {
		try {
			
			Optional<Patient> existPatient= patientService.findById(patient.getPatientId());
			if (existPatient.isEmpty()) {
				
				Patient savedPatient = patientService.save(patient);
				
				if (savedPatient != null) {
					return ResponseHandler.generateResponse(Constant.SUCCESS_ADD_MESSAGE, HttpStatus.CREATED,
							savedPatient);
				}
			}
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE_PATIENT_EXIST, HttpStatus.CONFLICT, null);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE, HttpStatus.CONFLICT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@PutMapping("/patients/{patientId}")
	public ResponseEntity<Object> updatePatient(@RequestBody Patient patient, @PathVariable Long patientId) {
		try {
			Optional<Patient> existPatient = patientService.findById(patientId);
			if (existPatient.isPresent()) {
				Patient searchedPatient = existPatient.get();
				
				searchedPatient.setPatientName(patient.getPatientName());			
				searchedPatient.setPatientAge(patient.getPatientAge());
//				searchedPatient.setPatientStatus(patient.getPatientStatus());
				Patient updatedPatient = patientService.save(searchedPatient);
				return ResponseHandler.generateResponse(Constant.UPDATE_SUCCESS_MESSAGE, HttpStatus.OK, updatedPatient);
			}
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE_PATIENT_NOT_PRESENT, HttpStatus.CONFLICT,
					null);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE, HttpStatus.CONFLICT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	@PutMapping("/patients/{patientId}/discharge")
	public ResponseEntity<Object> dischargePatient(@PathVariable Long patientId) {
		try {
			Optional<Patient> existPatient = patientService.findById(patientId);
			if (existPatient.isPresent()) {
				Patient searchedPatient = existPatient.get();
				if(searchedPatient.getPatientStatus().equals(Constant.DISCHARGED))
				{
					return ResponseHandler.generateResponse(Constant.PATIENT_ALREADY_DISCHARGED, HttpStatus.CONFLICT, null);
				}
//				searchedPatient.setPatientStatus(Constant.DISCHARGED);
//				Patient updatedPatient = patientService.save(searchedPatient);
				
				 patientService.dischargePatient(Constant.DISCHARGED, patientId);
				return ResponseHandler.generateResponse(Constant.UPDATE_SUCCESS_MESSAGE, HttpStatus.OK, Constant.DISCHARGE_SUCCESS);
			}
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE_PATIENT_NOT_PRESENT, HttpStatus.CONFLICT,
					null);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE, HttpStatus.CONFLICT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@DeleteMapping("/patients/{patientId}")
	public ResponseEntity<Object> deletePatient(@PathVariable Long patientId) {
		try {
			Optional<Patient> deletedPatient = patientService.findById(patientId);
			if (deletedPatient.isPresent()) {
				patientService.deleteById(patientId);
				return ResponseHandler.generateResponse(Constant.DELETE_SUCCESS_MESSAGE, HttpStatus.OK, deletedPatient);
			}
			return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.CONFLICT,null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
}
