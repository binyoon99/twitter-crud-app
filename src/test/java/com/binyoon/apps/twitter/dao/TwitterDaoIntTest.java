package com.binyoon.apps.twitter.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.binyoon.apps.twitter.dao.helper.HttpHelper;
import com.binyoon.apps.twitter.model.Tweet;
import com.binyoon.apps.twitter.utility.JsonUtil;
import com.binyoon.apps.twitter.utility.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {
  TwitterDao dao;
  String hashTag = "#abc";
  String text = "@heather Hello World" + hashTag + " " + System.currentTimeMillis();
  float lon = 22.16f;
  float lat = 43.322f;

  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey+"|"+consumerSecret+"|"+accessToken+"|"+tokenSecret);

    // setting dependency
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);

    // passing the dependency
    this.dao = new TwitterDao(httpHelper);

    // TwitterDaoUnitTest.java will use mockito to inject the mock class
  }

  @Test
  public void create()
      throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {

    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    try {
      System.out.println("Create() test :  ");
      System.out.println(JsonUtil.toJson( postTweet, true, true ));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Your Json is weird: " ,e);
    }

    Tweet tweet = dao.create(postTweet);
    assertEquals(text, tweet.getText());

    float [] actual = tweet.getCoordinates().getCoordinates();

    assertNotNull(tweet.getCoordinates());
    assertEquals(2, actual.length);
    assertEquals(lon, actual[0], 0.0001f);
    assertEquals(lat, actual[1], 0.0001f);
    assertTrue(hashTag.contains(postTweet.getEntities().getHashtags().get(0).getText()));

  }

  @Test
  public void findById() {
    String id = "1639121835366449152";
    Tweet tweet = dao.findById(id);

    String expectedText = "@heather Hello World#abc1849181";

    assertEquals(expectedText, tweet.getText());
    assertNotNull(tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getCoordinates().length);
    assertEquals(lon ,tweet.getCoordinates().getCoordinates()[0], 0.001);
    assertEquals(lat, tweet.getCoordinates().getCoordinates()[1], 0.001);
  }

  @Test
  public void deleteById()
      throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
    Tweet postTweet = dao.create(TweetUtil.buildTweet(text+"2", lon, lat));

    System.out.println("postTweet id string is : " +postTweet.getIdStr());
    Tweet tweet = dao.deleteById(postTweet.getIdStr());

    assertEquals(text+"2", tweet.getText());
    assertNotNull(tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getCoordinates().length);
    assertEquals(lon, tweet.getCoordinates().getCoordinates()[0], 0.0001f);
    assertEquals(lat, tweet.getCoordinates().getCoordinates()[1], 0.0001f);
  }
}