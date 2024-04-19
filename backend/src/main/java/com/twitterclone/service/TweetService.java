package com.twitterclone.service;

import java.util.List;

import com.twitterclone.exception.TweetException;
import com.twitterclone.exception.UserException;
import com.twitterclone.model.Tweet;
import com.twitterclone.model.User;
import com.twitterclone.request.TweetReplyRequest;

public interface TweetService {

	
	public Tweet createTweet(Tweet req, User user)throws UserException;
	
	public List<Tweet>findAllTweet();
	
	public Tweet retweet(Long tweetId, User user)throws UserException, TweetException;
	
	public Tweet findById(Long tweetId)throws TweetException;
	
	public void deleteTweetById(Long tweetId, Long userId)throws UserException, TweetException;
	
	public Tweet removeFromRetweet(Long tweetId, User user)throws UserException, TweetException;
	
	public Tweet createReply(TweetReplyRequest req, User user)throws UserException, TweetException;
	
	public List<Tweet>getUserTweet(User user);
	
	public List<Tweet>findByLikesContainsUser(User user);
}
