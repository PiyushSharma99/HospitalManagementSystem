package com.astrotalk.hospital.model;

import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Admission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long admissionId;
		
	
	private String assignedDoctor;

	
	private String patientRoomId;
	
	private String expenses;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="patient_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
    private Patient patient;

	public Long getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Long admissionId) {
		this.admissionId = admissionId;
	}

	public String getAssignedDoctor() {
		return assignedDoctor;
	}

	public void setAssignedDoctor(String assignedDoctor) {
		this.assignedDoctor = assignedDoctor;
	}

	public String getPatientRoomId() {
		return patientRoomId;
	}

	public void setPatientRoomId(String patientRoomId) {
		this.patientRoomId = patientRoomId;
	}

	public String getExpenses() {
		return expenses;
	}

	public void setExpenses(String expenses) {
		this.expenses = expenses;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	


}
