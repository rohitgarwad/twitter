package com.twitterclone.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitterclone.exception.TweetException;
import com.twitterclone.exception.UserException;
import com.twitterclone.model.Tweet;
import com.twitterclone.model.User;
import com.twitterclone.repository.TweetRepository;
import com.twitterclone.request.TweetReplyRequest;

@Service
public class TweetServiceImplementation implements TweetService {

	@Autowired
	private TweetRepository tweetRepository;
	
	@Override
	public Tweet createTweet(Tweet req, User user) throws UserException {
		Tweet tweet = new Tweet();
		tweet.setContent(req.getContent());
		tweet.setCreatedAt(LocalDateTime.now());
		tweet.setImage(req.getImage());
		tweet.setUser(user);
		tweet.setReply(false);
		tweet.setTweet(true);
		tweet.setVideo(req.getVideo());
		
		return tweetRepository.save(tweet);
	}

	@Override
	public List<Tweet> findAllTweet() {
		
		return tweetRepository.findAllByIsTweetTrueOrderByCreatedAtDesc();
	}

	@Override
	public Tweet retweet(Long tweetId, User user) throws UserException, TweetException {
		Tweet tweet = findById(tweetId);
		
		if(tweet.getRetweetUser().contains(user)) {
			tweet.getRetweetUser().remove(user);
		}
		else {
			tweet.getRetweetUser().add(user);
		}
		
		return tweetRepository.save(tweet);
	}

	@Override
	public Tweet findById(Long tweetId) throws TweetException {
		Tweet tweet = tweetRepository.findById(tweetId)
				.orElseThrow(()->new TweetException("Tweet not found with id "+tweetId));
		return tweet;
	}

	@Override
	public void deleteTweetById(Long tweetId, Long userId) throws UserException, TweetException {

		Tweet tweet = findById(tweetId);
		
		if(!userId.equals(tweet.getUser().getId())) {
			throw new UserException("you can't delete another user's tweet.");
		}
		
		if(tweet.isReply()) {
			Tweet t = findById(tweet.getReplyFor().getId());
			t.getReplyTweets().remove(tweet);
			tweet.setReplyFor(null);
			tweetRepository.save(t);
		}
			
			tweetRepository.deleteById(tweet.getId());
			
	}

	@Override
	public Tweet removeFromRetweet(Long tweetId, User user) throws UserException, TweetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tweet createReply(TweetReplyRequest req, User user) throws UserException, TweetException {
		
		Tweet replyFor = findById(req.getTweetId());
		
		Tweet tweet = new Tweet();
		tweet.setContent(req.getContent());
		tweet.setCreatedAt(LocalDateTime.now());
		tweet.setImage(req.getImage());
		tweet.setUser(user);
		tweet.setReply(true);
		tweet.setTweet(false);
		tweet.setReplyFor(replyFor);
		
		Tweet savedReply = tweetRepository.save(tweet);
		
		replyFor.getReplyTweets().add(savedReply);
		tweetRepository.save(replyFor);
		
		return replyFor;
	}

	@Override
	public List<Tweet> getUserTweet(User user) {
		
		return tweetRepository.findByRetweetUserContainsOrUser_IdAndIsTweetTrueOrderByCreatedAtDesc(user, user.getId());
	}

	@Override
	public List<Tweet> findByLikesContainsUser(User user) {
		
		return tweetRepository.findByLikesUser_Id(user.getId());
	}

}
