package com.foodler.orders.services;

import java.util.List;

import com.foodler.orders.resources.OrderInputResource;
import com.foodler.orders.resources.OrderItemOutputResource;
import com.foodler.orders.resources.OrderOutputResource;

public interface OrderItemsService {

	public OrderOutputResource createOrUpdateOrder(OrderInputResource orderInputResource) ;
	public List<OrderItemOutputResource> getOrderDetails(String orderId);
}
