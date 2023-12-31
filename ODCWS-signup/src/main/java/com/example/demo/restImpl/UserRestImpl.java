package com.example.demo.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.CarWashConstants;
import com.example.demo.rest.UserRest;
import com.example.demo.service.UserService;
import com.example.demo.utils.CarWashUtils;
import com.example.demo.wrapper.UserWrapper;

@RestController
@CrossOrigin(origins="*")
public class UserRestImpl implements UserRest {

	@Autowired
	UserService userService;

	@Override
	public ResponseEntity<String> signup(Map<String, String> requestMap) {
		try {
			return userService.signUp(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return CarWashUtils.getResponseEntity(CarWashConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		try {
			return userService.login(requestMap);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return CarWashUtils.getResponseEntity(CarWashConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<UserWrapper>> getAllUser() {
		try {
			return userService.getAllUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		try {
			return userService.update(requestMap);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return CarWashUtils.getResponseEntity(CarWashConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<String> checkToken() {
		// TODO Auto-generated method stub
		try {
			return userService.checkToken();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return CarWashUtils.getResponseEntity(CarWashConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
		try {
			return userService.changePassword(requestMap);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return CarWashUtils.getResponseEntity(CarWashConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
		try {
			return userService.forgotPassword(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CarWashUtils.getResponseEntity(CarWashConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<UserWrapper>> getAllWashers() {
		// TODO Auto-generated method stub
		try {
			return userService.getAllWashers();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
