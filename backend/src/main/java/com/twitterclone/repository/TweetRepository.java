package com.twitterclone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.twitterclone.model.Tweet;
import com.twitterclone.model.User;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

	List<Tweet>findAllByIsTweetTrueOrderByCreatedAtDesc();
	
	List<Tweet>findByRetweetUserContainsOrUser_IdAndIsTweetTrueOrderByCreatedAtDesc(User user, Long userId);
	
	List<Tweet>findByLikesContainingOrderByCreatedAtDesc(User user);
	
	@Query("Select t From Tweet t JOIN t.likes l where l.user.id=:userId")
	List<Tweet>findByLikesUser_Id(Long userId);
	
}
