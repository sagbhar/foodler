package com.foodler.orders.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.foodler.orders.vo.OrderItemsVo;
import com.foodler.orders.vo.OrdersVo;

public interface OrderItemsService {

	public ResponseEntity<OrdersVo> createOrUpdateOrder(OrderItemsVo itemsVo);
	public List<OrderItemsVo> getOrderDetails(String orderId);
}
