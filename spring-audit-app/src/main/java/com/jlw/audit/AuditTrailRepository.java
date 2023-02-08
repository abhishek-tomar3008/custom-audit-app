package com.jlw.audit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditTrailRepository extends JpaRepository<AuditTrail, Integer>{
	  List<AuditTrail> findByCreatedBy(String creater);

}
