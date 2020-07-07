package com.eatza.delivery.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.eatza.delivery.model.DeliveryRequestDto;
import com.eatza.delivery.service.impl.DeliveryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DeliveryController.class)
public class DeliveryControllerTest {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	@MockBean
	private DeliveryService deliveryService;
	@Autowired
	private ObjectMapper objectMapper;
	DeliveryRequestDto deliveryRequestDto;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		deliveryRequestDto = new DeliveryRequestDto();
	}

	@Test
	public void saveDelivery() throws Exception {
		deliveryRequestDto.setCustomerId(111L);
		deliveryRequestDto.setRestaurantId(22L);

		when(deliveryService.saveDeliveryDetails(any(DeliveryRequestDto.class)))
				.thenReturn("Delivery details successfully stored");
		RequestBuilder request = MockMvcRequestBuilders.post("/saveDelivery").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((deliveryRequestDto)));
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();
	}

	@Test
	public void saveDelivery_badRequest() throws Exception {
		deliveryRequestDto.setCustomerId(111L);
		deliveryRequestDto.setRestaurantId(22L);

		when(deliveryService.saveDeliveryDetails(any(DeliveryRequestDto.class)))
				.thenReturn("Delivery details successfully stored");
		RequestBuilder request = MockMvcRequestBuilders.post("/saveDelivery").contentType(MediaType.APPLICATION_XML)
				.content(objectMapper.writeValueAsString((deliveryRequestDto)));
		mockMvc.perform(request).andExpect(status().is(415)).andReturn();
	}

	@After
	public void cleanUp() {
		deliveryRequestDto = null;
	}
}
