package com.twitterclone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitterclone.dto.TweetDto;
import com.twitterclone.dto.mapper.TweetDtoMapper;
import com.twitterclone.exception.TweetException;
import com.twitterclone.exception.UserException;
import com.twitterclone.model.Tweet;
import com.twitterclone.model.User;
import com.twitterclone.request.TweetReplyRequest;
import com.twitterclone.response.ApiResponse;
import com.twitterclone.service.TweetService;
import com.twitterclone.service.UserService;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

	@Autowired
	private TweetService tweetService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<TweetDto> createTweet(@RequestBody Tweet req, 
			@RequestHeader("Authorization") String jwt) throws UserException, TweetException {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Tweet tweet = tweetService.createTweet(req, user);
		
		TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);
		
		return new ResponseEntity<>(tweetDto, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/reply")
	public ResponseEntity<TweetDto> replyTweet(@RequestBody TweetReplyRequest req, 
			@RequestHeader("Authorization") String jwt) throws UserException, TweetException {
				
		User user = userService.findUserProfileByJwt(jwt);

		Tweet tweet = tweetService.createReply(req, user);
				
		TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);
				
		return new ResponseEntity<>(tweetDto, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/{tweetId}/retweet")
	public ResponseEntity<TweetDto> retweet(@PathVariable Long tweetId, 
			@RequestHeader("Authorization") String jwt) throws UserException, TweetException {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Tweet tweet = tweetService.retweet(tweetId, user);
		
		TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);
		
		return new ResponseEntity<>(tweetDto, HttpStatus.OK);
		
	}
	
	@GetMapping("/{tweetId}")
	public ResponseEntity<TweetDto> findTweetById(@PathVariable Long tweetId, 
			@RequestHeader("Authorization") String jwt) throws UserException, TweetException {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Tweet tweet = tweetService.findById(tweetId);
		
		TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);
		
		
		return new ResponseEntity<>(tweetDto, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{tweetId}")
	public ResponseEntity<ApiResponse> deleteTweet(@PathVariable Long tweetId, 
			@RequestHeader("Authorization") String jwt) throws UserException, TweetException {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		tweetService.deleteTweetById(tweetId, user.getId());
		
		ApiResponse res = new ApiResponse();
		res.setMessage("Tweet Deleted Successfully");
		res.setStatus(true);
		
		
		return new ResponseEntity<>(res, HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<TweetDto>> getAllTweets( 
			@RequestHeader("Authorization") String jwt) throws UserException, TweetException {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		List<Tweet>tweets = tweetService.findAllTweet();
		
		List<TweetDto>tweetDtos = TweetDtoMapper.toTweetDtos(tweets, user);
		
		
		return new ResponseEntity<>(tweetDtos, HttpStatus.OK);
		
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<TweetDto>> getUsersAllTweets(@PathVariable Long userId,
			@RequestHeader("Authorization") String jwt) throws UserException, TweetException {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		List<Tweet>tweets = tweetService.getUserTweet(user);
		
		List<TweetDto>tweetDtos = TweetDtoMapper.toTweetDtos(tweets, user);
		
		
		return new ResponseEntity<>(tweetDtos, HttpStatus.OK);
		
	}
	
	@GetMapping("/user/{userId}/likes")
	public ResponseEntity<List<TweetDto>> findTweetByLikesContainsUser(@PathVariable Long userId,
			@RequestHeader("Authorization") String jwt) throws UserException, TweetException {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		List<Tweet>tweets = tweetService.findByLikesContainsUser(user);
		
		List<TweetDto>tweetDtos = TweetDtoMapper.toTweetDtos(tweets, user);
		
		
		return new ResponseEntity<>(tweetDtos, HttpStatus.OK);
		
	}
	
}
