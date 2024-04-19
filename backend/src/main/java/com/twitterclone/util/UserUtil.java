package com.twitterclone.util;

import java.util.List;

import com.twitterclone.model.User;

public class UserUtil {

	public static final boolean isReqUser(User reqUser, User user2) {
		return reqUser.getId().equals(user2.getId());
	}
	
	public static final boolean isFollowedByReqUser(User reqUser, User user2) {
		List<User> temp = reqUser.getFollowings();
		
		//System.out.println(temp.get(0));
		
		for(User users:temp) {
//			System.out.println("reqUsersId: "+ users.getId());
//			System.out.println("User2Id: "+ user2.getId());
			if(users.getId().equals(user2.getId())) {
//				System.out.println("Followed: "+ users.getId().equals(user2.getId()));
				return true;
			}
		}
		
		return false;
		
		//return reqUser.getFollowings().contains(user2);
	}
	
}
