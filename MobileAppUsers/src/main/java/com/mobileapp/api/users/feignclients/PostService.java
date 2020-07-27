package com.mobileapp.api.users.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mobileapp.api.users.circuitbreaker.postsFallBackFactory;
import com.mobileapp.api.users.ui.models.PostResposeModel;

@FeignClient(name="posts-ws", fallbackFactory = postsFallBackFactory.class)
public interface PostService{
	@GetMapping(value = "/users/{id}/postss")
	public List<PostResposeModel> getUserPosts(@PathVariable String id);

}
