package com.mobileapp.api.users.circuitbreaker;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mobileapp.api.users.feignclients.PostService;
import com.mobileapp.api.users.ui.models.PostResposeModel;

import feign.FeignException;

class postsFallBack implements PostService{

	Throwable cause;
	Logger logger=LoggerFactory.getLogger(this.getClass());
	public postsFallBack(Throwable cause) {

		this.cause=cause;
	}

	@Override
	public List<PostResposeModel> getUserPosts(String id) {
		// TODO Auto-generated method stub
		
		if((Exception)cause instanceof FeignException ) {
			if(((FeignException) this.cause).status() == 404)
			logger.error("404 error took place!");}
		else
			logger.error("ERRRRRROR"+ cause.getClass().getCanonicalName());
		return new ArrayList<>();
	}

}

 