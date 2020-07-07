package com.eatza.delivery.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.delivery.model.DeliveryRequestDto;
import com.eatza.delivery.service.impl.DeliveryService;

@RestController
public class DeliveryController {

	private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);
	@Autowired
	DeliveryService deliveryService;

	@PostMapping("saveDelivery")
	public ResponseEntity<String> saveDelivery(@RequestBody DeliveryRequestDto deliveryRequestDto) {
		String res = deliveryService.saveDeliveryDetails(deliveryRequestDto);
		logger.debug("Order Placed Successfully");
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

}
