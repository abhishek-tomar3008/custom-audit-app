package com.jlw.user;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jlw.audit.AuditService;
import com.jlw.audit.AuditTrail;
import com.jlw.audit.AuditTrailRepository;
import com.jlw.bank.Bank;
import com.jlw.bank.BankRepository;
import com.jlw.bank.BankService;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuditService auditService;
	
//	@Autowired
//	BankRepository bankRepository;
	@Autowired
	BankService bankService;
	
//	@Autowired
//	AuditTrailRepository auditTrailRepository;
	
	
	public User createUser(User user) {
		User createdUser=userRepository.save(user);
		bankService.setBankListFromUserService(user);
		
		
	 String creater=SecurityContextHolder.getContext().getAuthentication().getName();
//    AuditTrail audit=new AuditTrail();
//	  audit.setCreatedBy(creater);
//	  audit.setCreatedAt(LocalDateTime.now());
//	  audit.setActionType("post");
//	  audit.setAffectedClassName("User");
//	  audit.setAffectedRecordId(createdUser.getId());
//	  audit.setAffectedRecordName(createdUser.getName());
//	  audit.setModifiedAt(LocalDateTime.now());
//	  audit.setModifiedBy(creater);
//	  audit.setModifiedfield("-");
	 
	  auditService.auditTrailOfCreation(creater.toUpperCase(), LocalDateTime.now(), "User".toUpperCase(), user.getId(), user.getName().toUpperCase(),"post".toUpperCase());
	  
	 
//	 System.out.println(audit.getAffectedRecordName());
	
		
		return createdUser;
		
	}
	
	public User UpdateUser( User user) throws UserPrincipalNotFoundException {
	  Optional<User> userDb= userRepository.findById(user.getId());
	       
	  if(userDb.get()!=null) {
		  
		  bankService.updateBankListFromUserService(user);
		 
		  
			
			 String creater=SecurityContextHolder.getContext().getAuthentication().getName();
		   /* AuditTrail audit=new AuditTrail();
			  audit.setCreatedBy("-");
			  audit.setCreatedAt(LocalDateTime.now());
			  audit.setActionType("update");
			  audit.setAffectedClassName("User");
			  audit.setAffectedRecordId(user.getId());
			  audit.setAffectedRecordName(user.getName());
			  audit.setModifiedAt(LocalDateTime.now());
			  audit.setModifiedBy(creater);
			  */
			  
			  String fieldName="";
			  String oldField="";
			  String newField="";
			  if(!user.getName().equals(userDb.get().getName())) {
				  fieldName="name";
				  oldField=userDb.get().getName();
				  newField=user.getName();
				  String modifiedField=fieldName+" -"+oldField+" changed into "+newField;
				  
				  
				  auditService.auditTrailOfUpdateAndDelete(creater.toUpperCase(), LocalDateTime.now(), modifiedField.toUpperCase(), "User".toUpperCase(), userDb.get().getId(), user.getName().toUpperCase(), "update".toUpperCase());
				  
			  }if(!user.getEmail().equals(userDb.get().getEmail())) {
				  
				  fieldName="email";
				  oldField=userDb.get().getEmail();
				  newField=user.getEmail();
				  String modifiedField=fieldName+" -"+oldField+" changed into "+newField;
				  
				  
				  auditService.auditTrailOfUpdateAndDelete(creater.toUpperCase(), LocalDateTime.now(), modifiedField.toUpperCase(), "User".toUpperCase(), userDb.get().getId(), user.getName().toUpperCase(), "update".toUpperCase());
				  
			  }
			  
			   
			  
			  
			  
			  
			 
			  
//			  System.out.println(audit.getModifiedfield());
			  
			  //auditTrailRepository.save(audit);
		  
		  
		  
		  
		  
		  
		  
		  return userRepository.save(user);
	  }
	  else {
		  throw new UserPrincipalNotFoundException("User not found with the id "+user.getId());
	  }
		
		
		
	}

	public String deleteUser(int id) throws UserPrincipalNotFoundException {
		Optional<User> userDb= userRepository.findById(id);
		
		
		
		
		
		 if(userDb.get()!=null) {
			 
				
			String creater=SecurityContextHolder.getContext().getAuthentication().getName();
			 /* AuditTrail audit=new AuditTrail();
			  audit.setCreatedBy(creater);
			  audit.setCreatedAt(LocalDateTime.now());
			  audit.setActionType("deleted");
			  audit.setAffectedClassName("User");
			  audit.setAffectedRecordId(userDb.get().getId());
			  audit.setAffectedRecordName(userDb.get().getName());
			  audit.setModifiedAt(LocalDateTime.now());
			  audit.setModifiedBy(creater);
			  audit.setModifiedfield("-");*/
			 
			bankService.deleteBankListFromUserService(userDb.get());
			 auditService.auditTrailOfUpdateAndDelete(creater.toUpperCase(), LocalDateTime.now(), "All field deleted".toUpperCase(), "User".toUpperCase(), userDb.get().getId(), userDb.get().getName().toUpperCase(), "Deleted".toUpperCase());
			 
			 
			   userRepository.deleteById(id);
			   return "user deleted sucessfully";
		  }
		  else {
			  throw new UserPrincipalNotFoundException("User not found with the id "+id);
		  }
	  }
}
