package com.Niche;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.Niche.Application;
import com.Niche.model.ServiceRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceRequestControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllserviceRequests() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/service-requests",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetServiceRequestById() {
		ServiceRequest serviceRequest = restTemplate.getForObject(getRootUrl() + "/service-requests/1", ServiceRequest.class);
		System.out.println(serviceRequest.getEmailId());
		assertNotNull(serviceRequest);
	}

	@Test
	public void testCreateServiceRequest() {
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setEmailId("admin@gmail.com");
		serviceRequest.setSubject("cleaning");
		serviceRequest.setRequest("house cleaning");

		ResponseEntity<ServiceRequest> postResponse = restTemplate.postForEntity(getRootUrl() + "/service-requests", serviceRequest, ServiceRequest.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateEmployee() {
		int id = 1;
		ServiceRequest serviceRequest = restTemplate.getForObject(getRootUrl() + "/service-requests/" + id, ServiceRequest.class);
		serviceRequest.setEmailId("john@gmail.com");

		restTemplate.put(getRootUrl() + "/service-requests/" + id, serviceRequest);

		ServiceRequest updatedServiceRequest = restTemplate.getForObject(getRootUrl() + "/service-requests/" + id, ServiceRequest.class);
		assertNotNull(updatedServiceRequest);
	}

	@Test
	public void testDeleteEmployee() {
		int id = 2;
		ServiceRequest serviceRequest = restTemplate.getForObject(getRootUrl() + "/service-requests/" + id, ServiceRequest.class);
		assertNotNull(serviceRequest);

		restTemplate.delete(getRootUrl() + "/service-requests/" + id);

		try {
			serviceRequest = restTemplate.getForObject(getRootUrl() + "/service-requests/" + id, ServiceRequest.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
