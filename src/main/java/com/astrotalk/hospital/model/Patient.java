package com.astrotalk.hospital.model;


import java.util.ArrayList;


import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Patient {	
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long patientId;
		
	
		private String patientName;
		
	
		private Integer patientAge;	
		
		private String patientStatus;
		

		public String getPatientStatus() {
			return patientStatus;
		}

		public void setPatientStatus(String patientStatus) {
			this.patientStatus = patientStatus;
		}



		public Patient() {
			super();
		}


		public Patient(Long patientId, String patientName, Integer patientAge, String patientStatus) {
			super();
			this.patientId = patientId;
			this.patientName = patientName;
			this.patientAge = patientAge;
			this.patientStatus = patientStatus;
		}

		public Long getPatientId() {
			return patientId;
		}

		public void setPatientId(Long patientId) {
			this.patientId = patientId;
		}

		public String getPatientName() {
			return patientName;
		}

		public void setPatientName(String patientName) {
			this.patientName = patientName;
		}

		public Integer getPatientAge() {
			return patientAge;
		}

		public void setPatientAge(Integer patientAge) {
			this.patientAge = patientAge;
		}


}
