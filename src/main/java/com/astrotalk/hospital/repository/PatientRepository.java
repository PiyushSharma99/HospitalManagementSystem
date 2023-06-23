package com.astrotalk.hospital.repository;

import java.util.List;


//import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.astrotalk.hospital.model.Patient;

//@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	@Query(
			  value = "SELECT * FROM Patient p WHERE p.patient_status = ?1", 
			  nativeQuery = true)
	List<Patient> findAdmittedPatients(String status);
	
	
	@jakarta.transaction.Transactional
	@Modifying 
	@Query(
			  value = "UPDATE Patient p SET p.patient_status= ?1 WHERE p.patient_id = ?2", 
			  nativeQuery = true)
	void dischargePatient(String status, Long patient_id);
	
}
