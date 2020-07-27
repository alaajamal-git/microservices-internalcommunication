package com.mobileapp.api.users.userserviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.mobileapp.api.users.data.UserEntity;
import com.mobileapp.api.users.data.UserRepository;
import com.mobileapp.api.users.feignclients.PostService;
import com.mobileapp.api.users.shared.UserDto;
import com.mobileapp.api.users.ui.models.PostResposeModel;
import com.mobileapp.api.users.userservice.UserSevice;

@Service
public class UserserviceImp implements UserSevice {

	UserRepository userRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	//RestTemplate restt;
	PostService postService;
	Environment env;
	@Autowired
	public UserserviceImp(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder
			//,RestTemplate restt
			,PostService postService
			,Environment env
			) {
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
		this.userRepository=userRepository;
		//this.restt=restt;
		this.postService=postService;
		this.env=env;
	}
	
	@Override
	public UserDto createUser(UserDto user) {

		user.setUserId(UUID.randomUUID().toString());
		user.setHashPass(bCryptPasswordEncoder.encode(user.getPassword()));
		UserEntity userEntity;
		ModelMapper mapper= new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		userEntity=mapper.map(user, UserEntity.class);
		userRepository.save(userEntity);
		
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity=this.userRepository.findByEmail(username);
		if(userEntity==null) throw new UsernameNotFoundException(username);
		return new User(userEntity.getEmail(), userEntity.getHashPass(),true,true,true,true,new ArrayList<>());
	}

	@Override
	public UserDto getUserByUsername(String email) {
		UserEntity userEntity=this.userRepository.findByEmail(email);
		if(userEntity==null) throw new UsernameNotFoundException(email);
		
		return new ModelMapper().map(userEntity, UserDto.class);
	}

	@Override
	public UserDto getUserById(String id) {
		UserEntity userEntity=this.userRepository.findByUserId(id);
		UserDto userDto=new ModelMapper().map(userEntity, UserDto.class);
		if(userEntity==null) throw new UsernameNotFoundException(id);
	//	String postserviceurl=String.format(env.getProperty("posts.webservice.uri"), id);
	//	ResponseEntity<List<ResposePostModel>> entity=this.restt.exchange(postserviceurl,HttpMethod.GET ,null,new ParameterizedTypeReference<List<ResposePostModel>>() {});
	//	List<ResposePostModel> posts=entity.getBody();
		List<PostResposeModel> posts=postService.getUserPosts(id);
		userDto.setPosts(posts);
		
	 return userDto;
	}
	
	

}
