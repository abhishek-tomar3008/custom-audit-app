package com.jlw.audit;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AuditTrail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	

	
	
	  private String createdBy;

	  private LocalDateTime createdAt;
       private String modifiedBy;
	   private LocalDateTime modifiedAt;
	   
	   private String modifiedfield;
		private String affectedClassName;
		
		private int affectedRecordId;
		
		private String affectedRecordName;
		
		private String ActionType;
		
		
		

		public AuditTrail(String createdBy, LocalDateTime createdAt, String affectedClassName, int affectedRecordId,
				String affectedRecordName, String actionType) {
			super();
			this.createdBy = createdBy;
			this.createdAt = createdAt;
			this.affectedClassName = affectedClassName;
			this.affectedRecordId = affectedRecordId;
			this.affectedRecordName = affectedRecordName;
			ActionType = actionType;
		}
		
		

		public AuditTrail(String modifiedBy, LocalDateTime modifiedAt, String modifiedfield, String affectedClassName,
				int affectedRecordId, String affectedRecordName, String actionType) {
			super();
			this.modifiedBy = modifiedBy;
			this.modifiedAt = modifiedAt;
			this.modifiedfield = modifiedfield;
			this.affectedClassName = affectedClassName;
			this.affectedRecordId = affectedRecordId;
			this.affectedRecordName = affectedRecordName;
			ActionType = actionType;
		}
      


		
      


		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public String getModifiedBy() {
			return modifiedBy;
		}

		public void setModifiedBy(String modifiedBy) {
			this.modifiedBy = modifiedBy;
		}

		public LocalDateTime getModifiedAt() {
			return modifiedAt;
		}

		public void setModifiedAt(LocalDateTime modifiedAt) {
			this.modifiedAt = modifiedAt;
		}

		public String getModifiedfield() {
			return modifiedfield;
		}

		public void setModifiedfield(String modifiedfield) {
			this.modifiedfield = modifiedfield;
		}

		public String getAffectedClassName() {
			return affectedClassName;
		}

		public void setAffectedClassName(String affectedClassName) {
			this.affectedClassName = affectedClassName;
		}

		public int getAffectedRecordId() {
			return affectedRecordId;
		}

		public void setAffectedRecordId(int affectedRecordId) {
			this.affectedRecordId = affectedRecordId;
		}

		public String getAffectedRecordName() {
			return affectedRecordName;
		}

		public void setAffectedRecordName(String affectedRecordName) {
			this.affectedRecordName = affectedRecordName;
		}

		public String getActionType() {
			return ActionType;
		}

		public void setActionType(String actionType) {
			ActionType = actionType;
		}

		public AuditTrail(int id, String createdBy, LocalDateTime createdAt, String modifiedBy,
				LocalDateTime modifiedAt, String modifiedfield, String affectedClassName, int affectedRecordId,
				String affectedRecordName, String actionType) {
			super();
			this.id = id;
			this.createdBy = createdBy;
			this.createdAt = createdAt;
			this.modifiedBy = modifiedBy;
			this.modifiedAt = modifiedAt;
			this.modifiedfield = modifiedfield;
			this.affectedClassName = affectedClassName;
			this.affectedRecordId = affectedRecordId;
			this.affectedRecordName = affectedRecordName;
			ActionType = actionType;
		}

		
		public AuditTrail(String createdBy) {
			super();
			this.createdBy = createdBy;
		}

		public AuditTrail() {
			super();
		}
		
		
		
		
		

}
