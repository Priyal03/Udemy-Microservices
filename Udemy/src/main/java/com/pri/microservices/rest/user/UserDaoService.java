package com.pri.microservices.rest.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> userList = new ArrayList<>();
	
	static {
		
		userList.add(new User(1, "pri", new Date()));
		userList.add(new User(2, "duggu", new Date()));
		userList.add(new User(3, "bhensu", new Date()));
		
	}

	private static int userCount=3;
	
	public List<User> findAll(){
		return userList;
	}
	
	public User saveUser(User user) {
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		userList.add(user);
		return user;
	}
	
	public User findOne(int id) {
		
		for(User cur:userList) {
			
			if(cur.getId()==id) 
				return cur;
		}
		return null;
	}
	
	public User deleteOne(int id) {
		
		Iterator<User> it=userList.iterator();
		while(it.hasNext()) {
			
			User user =it.next();
			if(user.getId()==id) {
				it.remove();
				return user;
			}
		}
		return null;
	}

}
