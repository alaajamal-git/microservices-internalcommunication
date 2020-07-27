package com.mobileapp.posts.serviceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mobileapp.posts.services.PostServices;
import com.mobileapp.posts.shared.PostDto;
@Service
public class PostServicesImp implements PostServices {

	@Override
	public List<PostDto> getPosts(String id) {

		List<PostDto> list=new ArrayList<>();
		
		PostDto o1=new PostDto();
		o1.setUserId(id);
		o1.setDate(new Date());
		o1.setLikes(5455554444L);
		o1.setContent("the post content 1");
		
		PostDto o2=new PostDto();
		o2.setUserId(id);
		o2.setDate(new Date());
		o2.setLikes(5455554444L);
		o2.setContent("the post content 2");
		
		list.add(o2);
		list.add(o1);
		return list;
	}

}
