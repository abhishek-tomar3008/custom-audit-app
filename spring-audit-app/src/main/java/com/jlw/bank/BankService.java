package com.jlw.bank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jlw.audit.AuditService;
import com.jlw.user.User;

@Service
public class BankService {
	
	@Autowired
	BankRepository bankRepository;
	
	@Autowired
	AuditService auditService;
	
	//In this method i am tracking each creartion of bank record
	public void setBankListFromUserService(User user) {
		String creater=SecurityContextHolder.getContext().getAuthentication().getName();
		
		for(Bank bank:user.getBanks()) {
			bank.setUser(user);
			 auditService.auditTrailOfCreation(creater.toUpperCase(), LocalDateTime.now(), "Bank".toUpperCase(), bank.getAccountNo(), bank.getBranchName().toUpperCase(),"post".toUpperCase());
			
		}
		
	}
	
	//In this method i am tracking each updation of bank record
	public void updateBankListFromUserService(User user) {
		
          String creater=SecurityContextHolder.getContext().getAuthentication().getName();
		
		for(Bank bank:user.getBanks()) {

			Optional<Bank> bankDb= bankRepository.findById(bank.getAccountNo());
			bank.setUser(user);
			//bankRepository.save(bank);
			System.out.println(bank.getAccountNo());
			if(bankDb.isPresent()) {
				
			
				
				String oldVal=bankDb.get().getBranchName();
				
				String newVal=bank.getBranchName();
				
				String modifiedField="branchname -"+oldVal+" changed into "+newVal;
				auditService.auditTrailOfUpdateAndDelete(creater.toUpperCase(), LocalDateTime.now(), modifiedField.toUpperCase(), "Bank".toUpperCase(), bankDb.get().getAccountNo(), bank.getBranchName().toUpperCase(), "update".toUpperCase());
				
				
				
			}
			else {
				//System.out.println("abhi");
				
				
				auditService.auditTrailOfCreation(creater.toUpperCase(), LocalDateTime.now(), "Bank".toUpperCase(), bank.getAccountNo(),
						bank.getBranchName().toUpperCase(),"post".toUpperCase());
				
			}
			
		
			 
			
		}
		
		
	}
	
	
	public void deleteBankListFromUserService(User user) {
		
		String creater=SecurityContextHolder.getContext().getAuthentication().getName();
		
		
		for(Bank bank:user.getBanks()) {
			auditService.auditTrailOfUpdateAndDelete(creater.toUpperCase(), LocalDateTime.now(), "All field deleted".toUpperCase(), "Bank".toUpperCase(), bank.getAccountNo(), bank.getBranchName().toUpperCase(), "Delete".toUpperCase());
			

			
			
		}
		
		
	}
	
	

}
