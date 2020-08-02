package com.mobileapp.posts.restcontroller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.reflect.TypeToken;
import com.mobileapp.posts.models.ResposePostModel;
import com.mobileapp.posts.services.PostServices;
import com.mobileapp.posts.shared.PostDto;

@RestController
public class Posts {
	
	@Autowired
	private PostServices service;
	
	@RequestMapping("/users/{id}/posts")
	@GetMapping(produces = {MediaType.APPLICATION_ATOM_XML_VALUE,MediaType.APPLICATION_JSON_VALUE,})
	public List<ResposePostModel> getPosts(@PathVariable String id) {
		List<ResposePostModel> returnValue = new ArrayList<>();
		List<PostDto> postList=service.getPosts(id);
		if(postList==null) return returnValue;
				// mapping the two lists postList and postList

		
		Type listType = new TypeToken<List<ResposePostModel>>(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 4388705252532001293L;}.getType();
		returnValue=new ModelMapper().map(postList, listType);
		return returnValue;
	}

}
