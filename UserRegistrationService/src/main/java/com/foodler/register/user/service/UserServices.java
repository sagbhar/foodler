package com.foodler.register.user.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.foodler.register.user.register.vo.RegisterVo;

public interface UserServices {

	public RegisterVo saveUserDetails(RegisterVo userVo) throws Exception;
	
	public Boolean isValidUser(String emailId, String password, BCryptPasswordEncoder passwordEncoder);
	
	public RegisterVo findUserByUserName(String emailId);
	
	public List<RegisterVo> findAllUsers();
}
