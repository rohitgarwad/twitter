package com.twitterclone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitterclone.exception.TweetException;
import com.twitterclone.exception.UserException;
import com.twitterclone.model.Like;
import com.twitterclone.model.Tweet;
import com.twitterclone.model.User;
import com.twitterclone.repository.LikeRepository;
import com.twitterclone.repository.TweetRepository;

@Service
public class LikeServiceImplementation implements LikeService{
	
	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private TweetService tweetService;
	
	@Autowired
	private TweetRepository tweetRepository;

	@Override
	public Like likeTweet(Long tweetId, User user) throws UserException, TweetException {
		
		Like isLikeExist = likeRepository.isLikeExist(user.getId(), tweetId);
		
		if(isLikeExist!=null) {
			likeRepository.deleteById(isLikeExist.getId());
			return isLikeExist;
		}
		
		Tweet tweet = tweetService.findById(tweetId);
		
		Like like = new Like();
		like.setTweet(tweet);
		like.setUser(user);
		
		Like savedLike = likeRepository.save(like);
		
		tweet.getLikes().add(savedLike);
		tweetRepository.save(tweet);
		
		return savedLike;
	}

	@Override
	public List<Like> getAllLikes(Long tweetId) throws TweetException {
				
		List<Like>likes = likeRepository.findByTweetId(tweetId);
		
		return likes;
	}

}
