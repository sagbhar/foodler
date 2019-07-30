package com.foodler.orders.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.foodler.orders.resources.OrderInputResource;
import com.foodler.orders.resources.OrderOutputResource;
import com.foodler.orders.vo.OrderItemsVo;

public interface OrderItemsService {

	public OrderOutputResource createOrUpdateOrder(OrderInputResource orderInputResource) ;
	public List<OrderItemsVo> getOrderDetails(String orderId);
}
