package com.foodler.orders.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.foodler.orders.repository.OrderItemsRepository;
import com.foodler.orders.repository.OrdersRepository;
import com.foodler.orders.resources.OrderInputResource;
import com.foodler.orders.resources.OrderItemInputResource;
import com.foodler.orders.resources.OrderItemOutputResource;
import com.foodler.orders.resources.OrderOutputResource;
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
	public String getUserId(OrderInputResource itemDetails) {
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
	public OrdersVo saveOrder(OrderInputResource orderInputResource) {
		OrdersVo ordersVo = new OrdersVo();
		ordersVo.setStatus("P");
		ordersVo.setUserId(orderInputResource.getUserId());
		ordersVo.setTotalAmt(orderInputResource.getTotalAmt());
		ordersVo.setOrderId((String.valueOf(sequenceGenerator.generateSequence(ordersVo.SEQUENCE_NAME))));
		OrdersVo createdOdr = ordersRepo.insert(ordersVo);
		return createdOdr;
	}

	public OrdersVo updateOrder(OrderInputResource orderInputResource) {
		OrdersVo ordersVo = new OrdersVo();
		ordersVo.setOrderId(orderInputResource.getOrderId());
		ordersVo.setStatus("P");
		ordersVo.setUserId(orderInputResource.getUserId());
		ordersVo.setTotalAmt(orderInputResource.getTotalAmt());
		OrdersVo orderResp = ordersRepo.save(ordersVo);
		return orderResp;
	}

	/*
	 * @Override public ResponseEntity<OrdersVo>
	 * createOrUpdateOrder(OrderInputResource orderInputResource) { OrdersVo
	 * newOrder = new OrdersVo(); String userId = this.getUserId(itemDetails);
	 * itemDetails.setUserId(userId); String orderId =
	 * getPendingOrderIdforUser(itemDetails.getUserId());
	 * System.err.println("orderId" + orderId ); if (itemDetails.getOrderId() !=
	 * null || orderId != null ) { itemDetails.setOrderId(orderId); newOrder =
	 * this.updateOrder(itemDetails); } else { newOrder =
	 * this.saveOrder(itemDetails); itemDetails.setOrderId(newOrder.getOrderId()); }
	 * this.addOrderItem(itemDetails); return new
	 * ResponseEntity<OrdersVo>(newOrder,HttpStatus.OK); }
	 */

	
	@Override
	public OrderOutputResource createOrUpdateOrder(OrderInputResource orderInputResource) {
		OrdersVo newOrder = new OrdersVo();
		OrderOutputResource orderOutputRes = new OrderOutputResource();
		String userId = this.getUserId(orderInputResource);
		orderInputResource.setUserId(userId);
		if (orderInputResource.getOrderId() != null ) {
			newOrder = this.updateOrder(orderInputResource);
		} else {
			newOrder = this.saveOrder(orderInputResource);
			orderInputResource.setOrderId(newOrder.getOrderId());
		}
		if(orderInputResource.getOrderItems().size() >0) {
			for(OrderItemInputResource orderItemInputResource : orderInputResource.getOrderItems()) {
				this.addOrderItem(orderItemInputResource, newOrder.getOrderId());
			}
		}
		orderOutputRes.setOrderId(newOrder.getOrderId());
		return orderOutputRes;
	}
	
	// add the orderItems to the order
	public void addOrderItem(OrderItemInputResource orderItemInputResource,String orderId) {
		//List<OrderItemsVo> orderItemDetais = orderItemsRepo.findByOrderIdAndFoodItemId(itemDetails.getOrderId(),itemDetails.getFoodItemId());
		/*
		 * String orderItemId = null; if(orderItemDetais.size()>0) { orderItemId =
		 * orderItemDetais.get(0).getOrderItemId(); }
		 */
		OrderItemsVo orderItemsVo = new OrderItemsVo();
		orderItemsVo.setOrderId(orderId);
		orderItemsVo.setFoodItemId(orderItemInputResource.getFoodItemId());
		orderItemsVo.setOrderItemsPrice(orderItemInputResource.getOrderItemsPrice());
		orderItemsVo.setInventory(orderItemInputResource.getInventory());
		orderItemsVo.setStatus("P");
		orderItemsVo.setUserId(orderItemInputResource.getUserId());
		
		if(orderItemInputResource.getOrderItemId() != null) {
			orderItemsVo.setOrderItemId(orderItemInputResource.getOrderItemId());
			orderItemsRepo.save(orderItemsVo);
		}else {
		orderItemsVo.setOrderItemId((String.valueOf(sequenceGenerator.generateSequence(orderItemsVo.SEQUENCE_NAME))));
		orderItemsRepo.insert(orderItemsVo);
		}
	}
	
	@Override
	public List<OrderItemOutputResource> getOrderDetails(String orderId){
		List<OrderItemOutputResource> orderItemOutputList =new ArrayList<OrderItemOutputResource>();
		List<OrderItemsVo> itemsList = orderItemsRepo.findByOrderId(orderId);
		for(OrderItemsVo itemDetails : itemsList) {
			OrderItemOutputResource orderItemOutputResource = new OrderItemOutputResource();
			orderItemOutputResource.setFoodItemId(itemDetails.getFoodItemId());
			orderItemOutputResource.setOrderId(itemDetails.getOrderId());
			orderItemOutputResource.setOrderItemId(itemDetails.getOrderItemId());
			orderItemOutputResource.setInventory(itemDetails.getInventory());
			orderItemOutputResource.setStatus(itemDetails.getStatus());
			orderItemOutputResource.setUserId(itemDetails.getUserId());
			orderItemOutputList.add(orderItemOutputResource);
		}
		return orderItemOutputList;
	}
}
