package com.foodler.orders.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.foodler.orders.vo.OrderItemsVo;

@Repository
public interface OrderItemsRepository extends MongoRepository<OrderItemsVo, String> {
	
	public List<OrderItemsVo> findByOrderIdAndFoodItemId(String orderId, String foodItemId);
	public List<OrderItemsVo> findByOrderId(String orderId);
}
