package com.foodler.register.user.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.foodler.register.user.register.vo.RegisterVo;

public interface UserRepository extends MongoRepository<RegisterVo, String> {
	

	public RegisterVo findByEmailId(String emailId);

	public List<RegisterVo> findAll();
		
}