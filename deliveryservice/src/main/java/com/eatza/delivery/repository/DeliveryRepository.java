package com.eatza.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eatza.delivery.model.DeliveryEntity;

public interface DeliveryRepository extends  JpaRepository<DeliveryEntity, Long>{

}
