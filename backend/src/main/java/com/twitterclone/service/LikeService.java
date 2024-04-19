package com.twitterclone.service;

import java.util.List;

import com.twitterclone.exception.TweetException;
import com.twitterclone.exception.UserException;
import com.twitterclone.model.Like;
import com.twitterclone.model.User;

public interface LikeService {
	
	public Like likeTweet(Long tweetId, User user)throws UserException, TweetException;
	
	public List<Like>getAllLikes(Long tweetId)throws TweetException;
	
	
}
