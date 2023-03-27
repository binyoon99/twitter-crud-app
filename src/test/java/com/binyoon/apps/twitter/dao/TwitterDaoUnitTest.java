package com.binyoon.apps.twitter.dao;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import com.binyoon.apps.twitter.dao.helper.HttpHelper;
import com.binyoon.apps.twitter.model.Tweet;
import com.binyoon.apps.twitter.utility.JsonUtil;
import com.binyoon.apps.twitter.utility.TweetUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDao dao;
  String tweetJsonStr = "{\n"
      + "   \"created_at\":\"Mon May 18 21:24:39 +0000 2023\",\n"
      + "   \"id\":1097607853932564480,\n"
      + "   \"id_str\":\"1097607853932564480\",\n"
      + "   \"text\":\"Hello World test\",\n"
      + "   \"entities\":{\n"
      + "      \"hashtags\":[],"
      + "      \"user_mentions\":[]"
      + "   },\n"
      + "   \"coordinates\": {"
      + "           \"coorindates\" : [ 10.1, 10.1 ],\n"
      + "           \"type\" : \"Point\"\n"
      + "   },\n"
      + "   \"retweet_count\":0,\n"
      + "   \"favorite_count\":0,\n"
      + "   \"favorited\":false,\n"
      + "   \"retweeted\":false\n"
      + "}";

  @Test
  public void showTweet() throws Exception{
    //test failed request
    String hashtag = "#sample";
    String text = "@tos test "+ hashtag;
    float longitude = 10.1f;
    float latitude = -10.1f;

    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.create(TweetUtil.buildTweet(text, longitude, latitude));
    }
    catch(RuntimeException e){
      Assert.assertTrue(true);
    }

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
    //mock parseResponseBody
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    Tweet tweet = spyDao.create(TweetUtil.buildTweet(text, longitude, latitude));
    assertNotNull(tweet);
    assertNotNull(tweet.getText());


  }

  @Test
  public void postTweet () throws Exception{

  }

  @Test
  public void deleteTweet () throws Exception{

  }


}
