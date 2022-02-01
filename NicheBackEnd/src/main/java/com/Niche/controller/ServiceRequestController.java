package com.Niche.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Niche.exception.ResourceNotFoundException;
import com.Niche.model.ServiceRequest;
import com.Niche.repository.ServiceRequestRepository;

@RestController
@RequestMapping("/api")
public class ServiceRequestController {
	@Autowired
	private ServiceRequestRepository serviceRequestRepository;

	@GetMapping("/service-requests")
	public List<ServiceRequest> getAllServiceRequests(Principal principal) {
		return serviceRequestRepository.searchByUsername(principal.getName());
	}

	@GetMapping("/service-requests/{id}")
	public ResponseEntity<ServiceRequest> getServiceRequestById(@PathVariable(value = "id") Long serviceRequestId)
			throws ResourceNotFoundException {
		ServiceRequest serviceRequest = serviceRequestRepository.findById(serviceRequestId)
				.orElseThrow(() -> new ResourceNotFoundException("Service request not found for this id :: " + serviceRequestId));
		return ResponseEntity.ok().body(serviceRequest);
	}

	@PostMapping("/service-requests")
	public ServiceRequest createServiceRequest(@Valid @RequestBody ServiceRequest serviceRequest, Principal principal) {
		serviceRequest.setUsername(principal.getName());
		return serviceRequestRepository.save(serviceRequest);
	}

	@PutMapping("/service-requests/{id}")
	public ResponseEntity<ServiceRequest> updateServiceRequest(@PathVariable(value = "id") Long serviceRequestId,
			@Valid @RequestBody ServiceRequest serviceRequestDetails) throws ResourceNotFoundException {
		ServiceRequest serviceRequest = serviceRequestRepository.findById(serviceRequestId)
				.orElseThrow(() -> new ResourceNotFoundException("Service request not found for this id :: " + serviceRequestId));

		serviceRequest.setEmailId(serviceRequestDetails.getEmailId());
		serviceRequest.setSubject(serviceRequestDetails.getSubject());
		serviceRequest.setRequest(serviceRequestDetails.getRequest());
		final ServiceRequest updatedServiceRequest = serviceRequestRepository.save(serviceRequest);
		return ResponseEntity.ok(updatedServiceRequest);
	}

	@DeleteMapping("/service-requests/{id}")
	public Map<String, Boolean> deleteServiceRequest(@PathVariable(value = "id") Long serviceRequestId)
			throws ResourceNotFoundException {
		ServiceRequest serviceRequest = serviceRequestRepository.findById(serviceRequestId)
				.orElseThrow(() -> new ResourceNotFoundException("Service request not found for this id :: " + serviceRequestId));

		serviceRequestRepository.delete(serviceRequest);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
