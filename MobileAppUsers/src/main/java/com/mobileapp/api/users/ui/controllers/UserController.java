package com.mobileapp.api.users.ui.controllers;

import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mobileapp.api.users.shared.UserDto;
import com.mobileapp.api.users.ui.models.CreateUserRequestModel;
import com.mobileapp.api.users.ui.models.CreateUserResposeModel;
import com.mobileapp.api.users.ui.models.UserResponseModel;
import com.mobileapp.api.users.userservice.UserSevice;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	Environment env;
	@Autowired
	UserSevice userservice;
	@GetMapping("/status/check")
	public String status() {
		
		return "it is working on port: "+env.getProperty("local.server.port")+"   "+env.getProperty("token.date.expired");
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CreateUserResposeModel> createUser(@Valid @RequestBody CreateUserRequestModel user,Errors errors){

		//if(errors.hasErrors()) return new ResponseEntity<>(new RegistrationErr(errors),HttpStatus.BAD_REQUEST);
		ModelMapper mapper= new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userdto=mapper.map(user, UserDto.class);
		UserDto userDto=userservice.createUser(userdto);
		CreateUserResposeModel respose=mapper.map(userDto, CreateUserResposeModel.class);
		
		return new ResponseEntity<CreateUserResposeModel>(respose,HttpStatus.CREATED);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseModel> getUser(@PathVariable String id){
		UserDto userDto=userservice.getUserById(id);
		UserResponseModel userResponseModel=new ModelMapper().map(userDto, UserResponseModel.class);
		 
		return new ResponseEntity<UserResponseModel>(userResponseModel,HttpStatus.OK);
		
		
	}
	
}
