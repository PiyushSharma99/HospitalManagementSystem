package com.astrotalk.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.astrotalk.hospital.model.Admission;



@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Long>{
	
	@Query(
			  value = "SELECT * FROM Admission a WHERE a.patient_id = ?1", 
			  nativeQuery = true)
	List<Admission> findByPatientId(Long patientId);

	
}