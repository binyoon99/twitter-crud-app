package com.binyoon.apps.twitter.example;

import com.google.gdata.util.common.base.PercentEscaper;
import java.util.Arrays;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class TwitterApiTest {
  private static String CONSUMER_KEY = System.getenv("consumerKey");
  private static String CONSUMER_SECRET= System.getenv("consumerSecret");
  private static String ACCESS_TOKEN = System.getenv("accessToken");
  private static String TOKEN_SECRET = System.getenv("tokenSecret");

  public static void main(String[] args) throws Exception {

    //setup OAuth
    OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
    consumer.setTokenWithSecret(ACCESS_TOKEN,TOKEN_SECRET);

    // create a HTTP GET request
    String status = "today is a good day";
    PercentEscaper percentEscaper= new PercentEscaper("", false);
    HttpPost request = new HttpPost( "https://api.twitter.com/1.1/statuses/update.json?status=" + percentEscaper.escape((status)));

    // sign the request (add headers)
    consumer.sign(request);

    System.out.println("HTTP request headers: ");
    Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

    // send the request
    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpResponse response = httpClient.execute(request);
    System.out.println(EntityUtils.toString(response.getEntity()));
  }
}
