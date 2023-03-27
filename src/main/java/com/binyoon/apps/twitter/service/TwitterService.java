package com.binyoon.apps.twitter.service;

import com.binyoon.apps.twitter.dao.CrdDao;
import com.binyoon.apps.twitter.model.Tweet;
import java.util.List;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

public class TwitterService implements Service{
  private CrdDao dao;

  public TwitterService( CrdDao dao){
    this.dao = dao;
  }

  /**
   * Validate and post a user input Tweet
   *
   * @param tweet tweet to be created
   * @return created tweet
   *
   * @throws IllegalArgumentException if text exceed max number of allowed characters or lat/long out of range
   */
  @Override
  public Tweet postTweet(Tweet tweet)
      throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
    // Business logic : check text length , lat/lon range, id format
    validatePostTweet(tweet);

    // create tweet via dao
    return (Tweet) dao.create(tweet);
  }

  private Tweet validatePostTweet(Tweet tweet) {
  }


  /**
   * Search a tweet by ID
   *
   * @param id tweet id
   * @param fields set fields not in the list to null
   * @return Tweet object which is returned by the Twitter API
   *
   * @throws IllegalArgumentException if id or fields param is invalid
   */
  @Override
  public Tweet showTweet(String id, String[] fields) {
    return null;
  }
  /**
   * Delete Tweet(s) by id(s).
   *
   * @param ids tweet IDs which will be deleted
   * @return A list of Tweets
   *
   * @throws IllegalArgumentException if one of the IDs is invalid.
   */
  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    return null;
  }
}
