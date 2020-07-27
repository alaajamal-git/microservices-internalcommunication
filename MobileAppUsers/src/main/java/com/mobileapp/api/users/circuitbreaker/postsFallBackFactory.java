package com.mobileapp.api.users.circuitbreaker;

import org.springframework.stereotype.Component;

import com.mobileapp.api.users.feignclients.PostService;
import feign.hystrix.FallbackFactory;

@Component
public class postsFallBackFactory implements FallbackFactory<PostService>{

	@Override
	public PostService create(Throwable cause) {
		// TODO Auto-generated method stub
		return new postsFallBack(cause);
	}
}
 