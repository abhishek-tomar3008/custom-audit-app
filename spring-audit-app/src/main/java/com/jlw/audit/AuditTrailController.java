package com.jlw.audit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuditTrailController {
	
	@Autowired
	AuditService auditService;
	
	
	@GetMapping("/audit")
	public ResponseEntity<List<AuditTrail>> getAllAuditHandler(){
		
		return  new ResponseEntity<List<AuditTrail>>( auditService.getAllAuditRecords(),HttpStatus.OK);
	}
	
	
	@GetMapping("/audit/{creater}")
	public ResponseEntity<List<AuditTrail>> getAllAuditByCreaterNameHandler(@PathVariable String creater){
		
		return  new ResponseEntity<List<AuditTrail>>( auditService.getAllAuditRecordswithcreater(creater.toUpperCase()),HttpStatus.OK);
	}
	

}
