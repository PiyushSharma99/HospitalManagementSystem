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
import com.astrotalk.hospital.model.Admission;
import com.astrotalk.hospital.model.Patient;
import com.astrotalk.hospital.response.ResponseHandler;
import com.astrotalk.hospital.service.AdmissionService;
import com.astrotalk.hospital.service.PatientService;

@RestController
@RequestMapping("/hospital/users")
public class AdmissionController {
	@Autowired
	private AdmissionService admissionService;
	
	@Autowired
	private PatientService patientService;

	@GetMapping("/patients/{patientId}/admissions")
	public ResponseEntity<Object> getAllAdmissionsByPatientId(@PathVariable(value = "patientId") Long patientId) {
		try {
			Optional<Patient> searchedPatient = patientService.findById(patientId);
			if (searchedPatient.isEmpty()) {
				return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.NO_CONTENT, null);
			}
			
			List<Admission> admissions = admissionService.findByPatientId(patientId);

			return ResponseHandler.generateResponse(Constant.SUCCESS_GET_MESSAGE, HttpStatus.OK, admissions);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}


	@PostMapping("/patients/{patientId}/admissions")
	public ResponseEntity<Object> createAdmission(@PathVariable(value = "patientId") Long patientId, @RequestBody Admission admission) {
		try {
			
			Optional<Patient> existPatient= patientService.findById(patientId);

			if(existPatient.isEmpty())
			{
				return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.CONFLICT,null);
				
			}
			Admission savedAdmission = patientService.findById(patientId).map(patient -> {
				admission.setPatient(patient);
				return admissionService.save(admission);
			}).orElseThrow();
					
			return ResponseHandler.generateResponse(Constant.SUCCESS_ADD_MESSAGE, HttpStatus.CREATED,savedAdmission);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE, HttpStatus.CONFLICT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@PutMapping("/patients/{patientId}/admissions/{admissionId}")
	public ResponseEntity<Object> updateAdmission(@PathVariable(value = "patientId") Long patientId, @RequestBody Admission admission, @PathVariable Long admissionId) {
		try {
			
			Optional<Patient> existPatient= patientService.findById(patientId);

			if(existPatient.isEmpty())
			{
				return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.CONFLICT,null);
				
			}
			
			Optional<Admission> existAdmission = admissionService.findById(admissionId);
			if (existAdmission.isPresent()) {
				Admission searchedAdmission = existAdmission.get();
				
				searchedAdmission.setAssignedDoctor(admission.getAssignedDoctor());
				searchedAdmission.setExpenses(admission.getExpenses());
				searchedAdmission.setPatientRoomId(admission.getPatientRoomId());
			
				Admission updatedAdmission = admissionService.save(searchedAdmission);
				return ResponseHandler.generateResponse(Constant.UPDATE_SUCCESS_MESSAGE, HttpStatus.OK, updatedAdmission);
			}
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE_PATIENT_ADMISSION_NOT_PRESENT, HttpStatus.CONFLICT,
					null);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE, HttpStatus.CONFLICT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@DeleteMapping("/patients/{patientId}/admissions/{admissionId}")
	public ResponseEntity<Object> deleteAdmission(@PathVariable(value = "patientId") Long patientId, @PathVariable Long admissionId) {
		try {
			
			Optional<Patient> existPatient= patientService.findById(patientId);

			if(existPatient.isEmpty())
			{
				return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.CONFLICT,null);
				
			}
			
			Optional<Admission> deletedAdmission = admissionService.findById(admissionId);
			if (deletedAdmission.isPresent()) {
				admissionService.deleteById(admissionId);
				return ResponseHandler.generateResponse(Constant.DELETE_SUCCESS_MESSAGE, HttpStatus.OK, deletedAdmission);
			}
			return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.CONFLICT,null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
}
