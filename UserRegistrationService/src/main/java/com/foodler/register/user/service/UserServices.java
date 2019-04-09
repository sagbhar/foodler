package com.foodler.register.user.service;

import com.foodler.register.user.register.vo.RegisterVo;

public interface UserServices {

	public RegisterVo saveUserDetails(RegisterVo userVo);
	
	public RegisterVo findUserbyEmailId(String emailId);
}
