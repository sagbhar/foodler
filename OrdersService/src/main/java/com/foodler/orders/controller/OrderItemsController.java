package com.foodler.orders.controller;



	import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.foodler.orders.repository.OrderItemsRepository;
import com.foodler.orders.resources.UserDetailsVO;
import com.foodler.orders.sequence.SequenceGeneratorService;
import com.foodler.orders.vo.OrderItemsVo;


	@RestController
	@RequestMapping("/orderItems")
	public class OrderItemsController {
		
		@Autowired
		private SequenceGeneratorService sequenceGenerator;
		
		private OrderItemsRepository orderItemsRepository;

		public OrderItemsController(OrderItemsRepository orderItemsRepository) {
			super();
			this.orderItemsRepository = orderItemsRepository;
		}
		
		@PostMapping(value="/add")
		public void createOrderItem(@RequestBody OrderItemsVo itemsVo) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			System.err.println("principal value" + principal); 
			UriComponentsBuilder builder = UriComponentsBuilder
					.fromUriString("http://localhost:8765/register/findUserByEmailId/{username}");

			Map<String, String> userCredentialsMap = new HashMap<>();
			userCredentialsMap.put("username", principal.toString());
			RestTemplate restTemplate = new RestTemplate();
			UserDetailsVO response = restTemplate.getForObject(builder.build().toString(), UserDetailsVO.class,userCredentialsMap);
			System.err.println("Getting User Data" +response.getUserId());
			itemsVo.setUserId(response.getUserId());
			itemsVo.setOrderItemId((String.valueOf(sequenceGenerator.generateSequence(itemsVo.SEQUENCE_NAME))));
			this.orderItemsRepository.save(itemsVo);
		}
	}

