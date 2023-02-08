package com.jlw.audit;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
	
	@Autowired
	AuditTrailRepository auditTrailRepository;
	
	
	//this method will make a record only when any record will gonna created
	public void auditTrailOfCreation(String createdBy,
			                         LocalDateTime createdAt,
			                         String affectedClassName,
			                         int affectedRecordId,String affectedRecordName,
			                         String actionType) {
		
	AuditTrail auditTrail=	new AuditTrail( createdBy, createdAt, affectedClassName, affectedRecordId,
				affectedRecordName,actionType);
		auditTrailRepository.save(auditTrail);
		
		
		
	}
	
	//this method will make a record only when any record will gonna update
	public void auditTrailOfUpdateAndDelete(String modifiedBy, LocalDateTime modifiedAt, String modifiedfield, String affectedClassName,
			int affectedRecordId, String affectedRecordName, String actionType) {
	AuditTrail auditTrail=new AuditTrail(modifiedBy, modifiedAt, modifiedfield, affectedClassName, affectedRecordId, affectedRecordName, actionType);
	
	auditTrailRepository.save(auditTrail);
	}
	
	
	public List<AuditTrail> getAllAuditRecords(){
		
		return auditTrailRepository.findAll();
		
	}
	
	
	
     public List<AuditTrail> getAllAuditRecordswithcreater(String creater){
		
		return auditTrailRepository.findByCreatedBy(creater);
		
	 }
     
     
	
	
	
	
}
