package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.example.demo.POJO.User;
import com.example.demo.wrapper.UserWrapper;


public interface UserDao extends JpaRepository<User, Integer> {
		User findByEmail(@Param("email") String email);
		List<UserWrapper> getAllUser();
		List<String> getAllAdmin();
		
		List<UserWrapper> getAllWashers(String role);
		@Transactional
		@Modifying
		Integer updateStatus(@Param("status") String status, @Param("id") Integer id);
		
	
}
