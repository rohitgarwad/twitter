package com.twitterclone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitterclone.dto.LikeDto;
import com.twitterclone.dto.mapper.LikeDtoMapper;
import com.twitterclone.exception.TweetException;
import com.twitterclone.exception.UserException;
import com.twitterclone.model.Like;
import com.twitterclone.model.User;
import com.twitterclone.service.LikeService;
import com.twitterclone.service.UserService;

@RestController
@RequestMapping("/api")
public class LikeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private LikeService likeService;
	
	@PostMapping("/{tweetId}/likes")
	public ResponseEntity<LikeDto>likeTweet(@PathVariable Long tweetId,
			@RequestHeader("Authorization")String jwt)throws UserException, TweetException {
		
		User user = userService.findUserProfileByJwt(jwt);
		Like like = likeService.likeTweet(tweetId, user);
		
		LikeDto likeDto = LikeDtoMapper.toLikeDto(like, user);
		
		return new ResponseEntity<LikeDto>(likeDto, HttpStatus.CREATED);
	}
	
	@PostMapping("/tweet/{tweetId}")
	public ResponseEntity<List<LikeDto>>getAllLikes(@PathVariable Long tweetId,
			@RequestHeader("Authorization")String jwt)throws UserException, TweetException {
		
		User user = userService.findUserProfileByJwt(jwt);
		List<Like>like = likeService.getAllLikes(tweetId);
		
		List<LikeDto> likeDtos = LikeDtoMapper.toLikeDtos(like, user);
		
		return new ResponseEntity<>(likeDtos, HttpStatus.CREATED);
	}
	
	
}
