package com.Niche.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Niche.model.ServiceRequest;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long>{
	@Query("SELECT r FROM ServiceRequest r WHERE r.username LIKE %?1%")
	public List<ServiceRequest> searchByUsername(String username);
}
