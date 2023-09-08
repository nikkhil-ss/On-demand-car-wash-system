package com.example.demo.JWT;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerUserdetailsService implements UserDetailsService {

	@Autowired
	UserDao userdao;
	
	private com.example.demo.POJO.User userDetail;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Inside loadUserByusename {}",username);
		userDetail=userdao.findByEmail(username);
		if(!Objects.isNull(userDetail)) {
			return new User(userDetail.getEmail(),userDetail.getPassword(),new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("User not found");
		}
	}
	
	public com.example.demo.POJO.User getUserDetail(){
		return userDetail;
	}

}
