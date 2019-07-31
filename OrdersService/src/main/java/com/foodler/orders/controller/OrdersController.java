package com.foodler.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodler.orders.resources.OrderInputResource;
import com.foodler.orders.resources.OrderItemOutputResource;
import com.foodler.orders.resources.OrderOutputResource;
import com.foodler.orders.services.OrderItemsServiceImpl;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrderItemsServiceImpl orderItemService;

	
	/*
	 * @PostMapping(value="/add") public ResponseEntity<OrdersVo>
	 * createOrder(@RequestBody OrderItemsVo itemsVo) { ResponseEntity<OrdersVo>
	 * orderResp = orderItemService.createOrUpdateOrder(itemsVo); return orderResp;
	 * }
	 */
	
	@PostMapping(value="/addOrder")
	public ResponseEntity<OrderOutputResource> createOrderNew(@RequestBody OrderInputResource orderInputResource) {
		OrderOutputResource orderOutputRes = orderItemService.createOrUpdateOrder(orderInputResource);
		return new ResponseEntity<OrderOutputResource>(orderOutputRes,HttpStatus.OK);
	}
	
	@GetMapping(value="/listOrderItems/{orderId}")
	public ResponseEntity<List<OrderItemOutputResource>> getAllOrderItems(@RequestParam String orderId) {
		List<OrderItemOutputResource> itemsList =  orderItemService.getOrderDetails(orderId);
		return new ResponseEntity<List<OrderItemOutputResource>>(itemsList,HttpStatus.OK);
	}
}
