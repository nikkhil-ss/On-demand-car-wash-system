package com.example.demo.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.wrapper.UserWrapper;

@RequestMapping(path="/api/auth")
@CrossOrigin(origins = "*")
public interface UserRest {
	
		@PostMapping("/register")
		public ResponseEntity<String> signup(@RequestBody(required=true) Map<String,String> requestMap);
		
		@PostMapping("/login")
		public ResponseEntity<String> login(@RequestBody(required=true) Map<String,String> requestMap);
		
		@GetMapping("/getAllUsers")
		public ResponseEntity<List<UserWrapper>> getAllUser();
		
		@GetMapping("/getAllWashers")
		public ResponseEntity<List<UserWrapper>> getAllWashers();
		
		@PostMapping("/updateStatus")
		public ResponseEntity<String> update(@RequestBody Map<String,String> requestMap);
		
		@GetMapping("/checkToken")
		public ResponseEntity<String> checkToken();
		
		@PostMapping("/changePassword")
		ResponseEntity<String> changePassword(@RequestBody Map<String,String> requestMap);
		
		@PostMapping("/forgotPassword")
		ResponseEntity<String> forgotPassword(@RequestBody Map<String,String> requestMap);
}
