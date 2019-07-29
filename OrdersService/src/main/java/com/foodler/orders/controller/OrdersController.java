package com.foodler.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodler.orders.services.OrderItemsServiceImpl;
import com.foodler.orders.vo.OrderItemsVo;
import com.foodler.orders.vo.OrdersVo;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrderItemsServiceImpl orderItemService;

	
	@PostMapping(value="/add")
	public ResponseEntity<OrdersVo> createOrder(@RequestBody OrderItemsVo itemsVo) {
		ResponseEntity<OrdersVo> orderResp = orderItemService.createOrUpdateOrder(itemsVo);
		return orderResp;
	}
	
	@GetMapping(value="/listOrderItems/{orderId}")
	public List<OrderItemsVo> getAllOrderItems(@RequestParam String orderId) {
		return orderItemService.getOrderDetails(orderId);
	}
}
