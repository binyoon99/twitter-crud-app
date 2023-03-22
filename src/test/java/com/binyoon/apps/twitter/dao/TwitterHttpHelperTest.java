package com.binyoon.apps.twitter.dao;

import com.binyoon.apps.twitter.dao.helper.HttpHelper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

public class TwitterHttpHelperTest {

  TwitterHttpHelper twitterHttpHelper;

  @Before
  public void setUp(){
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey+"|"+consumerSecret+"|"+accessToken+"|"+tokenSecret);
    //Create components
    twitterHttpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);

  }
  @Test
  public void httpPost()
      throws IOException, URISyntaxException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
    HttpResponse response = twitterHttpHelper.httpPost(new URI(
        "https://api.twitter.com/1.1/statuses/update.json?status=Hello"
    ));
    System.out.println(EntityUtils.toString(((HttpResponse) response).getEntity()));
  }

  @Test
  public void httpGet() throws IOException, URISyntaxException {
    HttpResponse response = twitterHttpHelper.httpGet(new URI(
        "https://api.twitter.com/1.1/statuses/show.json?id=1638402004828430336"
    ));
    System.out.println(EntityUtils.toString(((HttpResponse) response).getEntity()));
  }
}