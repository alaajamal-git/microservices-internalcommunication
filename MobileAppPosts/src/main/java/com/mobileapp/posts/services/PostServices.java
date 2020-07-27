package com.mobileapp.posts.services;

import java.util.List;

import com.mobileapp.posts.shared.PostDto;

public interface PostServices {
	
	List<PostDto> getPosts(String id);

}
