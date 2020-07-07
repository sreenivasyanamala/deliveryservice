package com.eatza.delivery.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.eatza.delivery.model.DeliveryEntity;
import com.eatza.delivery.model.DeliveryRequestDto;
import com.eatza.delivery.repository.DeliveryRepository;
import com.eatza.delivery.service.impl.DeliveryService;

public class DeliveryServiceTest {

	@Mock
	DeliveryRepository deliveryRepository;

	@InjectMocks
	DeliveryService deliveryService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveDelivery() {
		DeliveryRequestDto dto = new DeliveryRequestDto();
		dto.setCustomerId(55L);
		dto.setRestaurantId(4L);
		DeliveryEntity entity = new DeliveryEntity();
		entity.setCustomerId(55L);
		entity.setRestaurantId(6L);
		entity.setId(2L);
		entity.setStatus("SCHEDULED");
		String returnedItem = deliveryService.saveDeliveryDetails(dto);
		assertEquals("Delivery details are stored successfully", returnedItem);
		assertEquals("SCHEDULED", entity.getStatus());
		assertNotNull(entity.getCustomerId());
		assertNotNull(entity.getRestaurantId());
		assertNotNull(entity.getId());
	}
	
	@After
	public void tearDown() {
		System.out.println("Test cases completed successfully in Deliveryservice class ");
	}
}
