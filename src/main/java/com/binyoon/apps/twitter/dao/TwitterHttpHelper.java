package com.binyoon.apps.twitter.dao;

import com.binyoon.apps.twitter.dao.helper.HttpHelper;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpMethod;

public class TwitterHttpHelper implements HttpHelper {


  private OAuthConsumer consumer;
  private CloseableHttpClient httpClient;
  private HttpResponse response ;

  /**
   * Constructor
   * @param consumerKey
   * @param consumerSecret
   * @param accessToken
   * @param tokenSecret
   */
  public TwitterHttpHelper(String consumerKey, String consumerSecret,
      String accessToken, String tokenSecret){
      consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
      consumer.setTokenWithSecret(accessToken,tokenSecret);
      httpClient = HttpClientBuilder.create().build();
  }
  // Parameterless  constructor
  public TwitterHttpHelper() {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET= System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");
    consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
    consumer.setTokenWithSecret(ACCESS_TOKEN,TOKEN_SECRET);
    httpClient = HttpClientBuilder.create().build();

  }
  /**
   * Execute a HTTP Post call
   * @param uri
   * @return
   */
  @Override
  public HttpResponse httpPost(URI uri)
      throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
    /*
    HttpPost request = new HttpPost(uri);
        consumer.sign(request);
    try {
      response = httpClient.execute(request);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return response;
     */
    // we can make above code more sophisticated
    try {
      return httpCall(HttpMethod.POST, uri, null);
    } catch (OAuthException | IOException e) {
      throw new RuntimeException("RuntimeException: "+e);
    }
  }
  /*
    getEntity() to retrieve the response body of an HTTP request, and then use EntityUtil to parse the response body into a more structured format (e.g., JSON or XML).
    stringEntity is the raw data that you send in the request which
    would've used stringEntity if update tweet was possible.
  */
public HttpResponse httpCall(HttpMethod method,URI uri, String stringEntity )
    throws OAuthException, IOException {
    if (method == HttpMethod.POST){

        HttpPost request = new HttpPost(uri);
        consumer.sign(request);
        response = httpClient.execute(request);

      }else if (method == HttpMethod.GET){

        HttpGet request = new HttpGet(uri);
        consumer.sign(request);
        response = httpClient.execute(request);

    }else {
      throw new IllegalArgumentException("Unknown HTTP Method: " + method.name());
    }
    return response;
  }
  /**
   * Execute a HTTP Get call
   * @param uri
   * @return
   */
  @Override
  public HttpResponse httpGet(URI uri) {
    try {
      return httpCall(HttpMethod.GET, uri, null);
    } catch (OAuthException | IOException e) {
      throw new RuntimeException("RuntimeException: "+e);
    }
  }
}
