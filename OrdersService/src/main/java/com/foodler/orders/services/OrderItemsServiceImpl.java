package com.foodler.orders.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.foodler.orders.repository.OrderItemsRepository;
import com.foodler.orders.repository.OrdersRepository;
import com.foodler.orders.resources.UserDetailsVO;
import com.foodler.orders.sequence.SequenceGeneratorService;
import com.foodler.orders.vo.OrderItemsVo;
import com.foodler.orders.vo.OrdersVo;

@Component
public class OrderItemsServiceImpl implements OrderItemsService {
	@Autowired
	OrderItemsRepository orderItemsRepo;

	@Autowired
	OrdersRepository ordersRepo;

	@Autowired
	private SequenceGeneratorService sequenceGenerator;

	// get userDetails
	public String getUserId(OrderItemsVo itemDetails) {
		String userId = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.err.println("principal value" + principal);
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUriString("http://localhost:8765/register/findUserByEmailId/{username}");

		Map<String, String> userCredentialsMap = new HashMap<>();
		userCredentialsMap.put("username", principal.toString());
		RestTemplate restTemplate = new RestTemplate();
		UserDetailsVO response = restTemplate.getForObject(builder.build().toString(), UserDetailsVO.class,
				userCredentialsMap);
		userId = response.getUserId();
		return userId;
	}

	// get orders in any pending state for user
	public String getPendingOrderIdforUser(String userId) {
		String orderId = null;
		List<OrdersVo> ordAssUser = ordersRepo.findByUserIdAndStatus(userId, "P");
		if (ordAssUser.size() > 0) {
			orderId = ordAssUser.get(0).getOrderId();
		}
		return orderId;
	}

	// create new order
	public OrdersVo saveOrder(OrderItemsVo itemDetails) {
		OrdersVo ordersVo = new OrdersVo();
		Double totalAmt = Double.parseDouble(itemDetails.getInventory()) * itemDetails.getOrderItemsPrice();
		ordersVo.setStatus("P");
		ordersVo.setUserId(itemDetails.getUserId());
		ordersVo.setTotalAmt(totalAmt);
		ordersVo.setOrderId((String.valueOf(sequenceGenerator.generateSequence(ordersVo.SEQUENCE_NAME))));
		OrdersVo createdOdr = ordersRepo.insert(ordersVo);
		return createdOdr;
	}

	public OrdersVo updateOrder(OrderItemsVo itemDetails) {
		OrdersVo ordersVo = new OrdersVo();
		Double totalAmt = Double.parseDouble(itemDetails.getInventory()) * itemDetails.getOrderItemsPrice();
		ordersVo.setOrderId(itemDetails.getOrderId());
		ordersVo.setStatus("P");
		ordersVo.setUserId(itemDetails.getUserId());
		ordersVo.setTotalAmt(totalAmt);
		OrdersVo orderResp = ordersRepo.save(ordersVo);
		return orderResp;
	}

	@Override
	public ResponseEntity<OrdersVo> createOrUpdateOrder(OrderItemsVo itemDetails) {
		OrdersVo newOrder = new OrdersVo();
		String userId = this.getUserId(itemDetails);
		itemDetails.setUserId(userId);
		String orderId = getPendingOrderIdforUser(itemDetails.getUserId());
		System.err.println("orderId" + orderId );
		if (itemDetails.getOrderId() != null || orderId != null ) {
			itemDetails.setOrderId(orderId);
			newOrder = this.updateOrder(itemDetails);
		} else {
			newOrder = this.saveOrder(itemDetails);
			itemDetails.setOrderId(newOrder.getOrderId());
		}
		this.addOrderItem(itemDetails);
		return new ResponseEntity<OrdersVo>(newOrder,HttpStatus.OK);
	}

	// add the orderItems to the order
	public void addOrderItem(OrderItemsVo itemDetails) {
		List<OrderItemsVo> orderItemDetais = orderItemsRepo.findByOrderIdAndFoodItemId(itemDetails.getOrderId(),itemDetails.getFoodItemId());
		String orderItemId = null;
		if(orderItemDetais.size()>0) {
			orderItemId = orderItemDetais.get(0).getOrderItemId();
		}
		if(orderItemId != null) {
			itemDetails.setOrderItemId(orderItemId);
			orderItemsRepo.save(itemDetails);
		}else {
		itemDetails.setOrderItemId((String.valueOf(sequenceGenerator.generateSequence(itemDetails.SEQUENCE_NAME))));
		orderItemsRepo.insert(itemDetails);
		}
	}
	
	@Override
	public List<OrderItemsVo> getOrderDetails(String orderId){
		return(orderItemsRepo.findByOrderId(orderId));
	}
}
