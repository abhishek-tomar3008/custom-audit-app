package com.jlw.bank;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jlw.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Bank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountNo;
	
	
	private  String branchName;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;

	public Bank(int accountNo, String branchname, User user) {
		super();
		this.accountNo = accountNo;
		this.branchName = branchname;
		this.user = user;
	}
    
	public Bank() {
		super();
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
