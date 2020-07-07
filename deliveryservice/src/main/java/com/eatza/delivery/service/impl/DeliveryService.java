package com.eatza.delivery.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.eatza.delivery.model.DeliveryEntity;
import com.eatza.delivery.model.DeliveryRequestDto;
import com.eatza.delivery.repository.DeliveryRepository;

@Service
public class DeliveryService {

	@Autowired
	DeliveryRepository deliveryRepository;
    
	private static final Logger logger = LoggerFactory.getLogger(DeliveryService.class);
	
	@KafkaListener(topics = "kafka_delivery_json", groupId = "group_json", containerFactory = "userKafkaListenerFactory")
	public String saveDeliveryDetails(DeliveryRequestDto deliveryRequest) {
		logger.debug("In place delivery method, creating delivery object to persist");

		DeliveryEntity entity = new DeliveryEntity();
		entity.setCustomerId(deliveryRequest.getCustomerId());
		entity.setRestaurantId(deliveryRequest.getRestaurantId());
		entity.setStatus("SCHEDULED");                
		 deliveryRepository.save(entity);
		
		return "Delivery details are stored successfully";
	}
}