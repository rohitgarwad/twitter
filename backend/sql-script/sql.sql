show databases;
create database twitter_clone;
use twitter_clone;

show tables;
select * from likes;
select * from likes_seq;
select * from tweet;
select * from tweet_reply_tweets;
select * from tweet_retweet_user;
select * from tweet_seq;
select * from user;
select * from user_followers;
select * from user_followings;
select * from user_seq;

truncate user;
delete from tweet where id = 2;
truncate table tweet_reply_tweets;

describe tweet_reply_tweets;

SET FOREIGN_KEY_CHECKS=ON;
ALTER TABLE tweet_reply_tweets DROP FOREIGN KEY FKpdu2nnuuo0ld9sfsxwicpqti3;
ALTER TABLE tweet_reply_tweets ADD CONSTRAINT FK9i1snx5mtapsq8iwp7o10u1vn FOREIGN KEY (reply_tweets_id) REFERENCES tweet (id) ON DELETE CASCADE;

SELECT 
  TABLE_NAME,COLUMN_NAME,CONSTRAINT_NAME, REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME
FROM
  INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE
  REFERENCED_TABLE_SCHEMA = (SELECT DATABASE()) AND
  REFERENCED_TABLE_NAME = 'tweet';


drop database twitter_clone;

SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE TABLE_NAME = 'tweet';