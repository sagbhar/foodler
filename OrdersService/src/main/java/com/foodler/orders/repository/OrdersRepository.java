package com.foodler.orders.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.foodler.orders.vo.OrdersVo;

@Repository
public interface OrdersRepository extends MongoRepository<OrdersVo, String>{
	
	public List<OrdersVo> findByUserId(String userId);
	public List<OrdersVo> findByUserIdAndStatus(String userId, String status);

}
	