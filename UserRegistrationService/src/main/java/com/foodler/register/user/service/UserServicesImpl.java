/**
 * 
 */
package com.foodler.register.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foodler.register.user.register.vo.RegisterVo;
import com.foodler.register.user.repository.UserRepository;

/**
 * @author sagbhar
 *
 */
@Component
public class UserServicesImpl implements UserServices {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public RegisterVo saveUserDetails(RegisterVo userVo) {
		return userRepo.save(userVo);
	}

	@Override
	public RegisterVo findUserbyEmailId(String emailId) {
		return userRepo.findByEmailId(emailId);
		
	}
}
