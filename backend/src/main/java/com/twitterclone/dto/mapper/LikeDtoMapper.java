package com.twitterclone.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.twitterclone.dto.LikeDto;
import com.twitterclone.dto.TweetDto;
import com.twitterclone.dto.UserDto;
import com.twitterclone.model.Like;
import com.twitterclone.model.User;

public class LikeDtoMapper {

	public static LikeDto toLikeDto(Like like, User reqUser) {
		
		UserDto user = UserDtoMapper.toUserDto(like.getUser());
		TweetDto tweet = TweetDtoMapper.toTweetDto(like.getTweet(), reqUser);
		
		LikeDto likeDto = new LikeDto();
		likeDto.setId(like.getId());
		likeDto.setTweet(tweet);
		likeDto.setUser(user);
		
		
		return likeDto;
	}
	
	public static List<LikeDto>toLikeDtos(List<Like>likes, User reqUser) {
		List<LikeDto>likeDtos = new ArrayList<>();
		
		for(Like like : likes) {
			UserDto user = UserDtoMapper.toUserDto(like.getUser());
			TweetDto tweet = TweetDtoMapper.toTweetDto(like.getTweet(), reqUser);
			
			LikeDto likeDto = new LikeDto();
			likeDto.setId(like.getId());
			likeDto.setTweet(tweet);
			likeDto.setUser(user);
			likeDtos.add(likeDto);
		}
		return likeDtos;
	}
	
}
