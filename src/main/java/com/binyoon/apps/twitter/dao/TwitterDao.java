package com.binyoon.apps.twitter.dao;

import com.binyoon.apps.twitter.dao.helper.HttpHelper;
import com.binyoon.apps.twitter.model.Coordinates;
import com.binyoon.apps.twitter.model.Entities;
import com.binyoon.apps.twitter.model.Hashtag;
import com.binyoon.apps.twitter.model.Tweet;
import com.binyoon.apps.twitter.utility.JsonUtil;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TwitterDao implements CrdDao<Tweet, String> {

  //URI constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy";
  //URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  //Response code
  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;

  final Logger logger = LoggerFactory.getLogger(TwitterDao.class);

  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;

  }

  /**
   * Create an entity(Tweet) to the underlying storage
   *
   * @param entity entity that to be created
   * @return created entity
   */
  @Override
  public Tweet create(Tweet entity)
      throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
    // Construct URI
    URI uri;
    PercentEscaper percentEscaper = new PercentEscaper("", false);

    String tweetText = entity.getText();
    /*
     * Usage : TwitterApp "post" "tweet_text" "latitude:longitude"
     * post : https://api.twitter.com/1.1/statuses/update.json
     * tweet_text :  parameter : status -> the text of the status update
     * Coordinate contains the longitude and latitude of the Tweet's location, as a collection in the form [longitude, latitude]
     * example : https://api.twitter.com/1.1/statuses/update.json?status=HelloWorld&long=37.78217.9&lat=-122.40062
     */
    // tweet -> coordinate instance -> get coordinate float[longitude, latitude]
    Coordinates coordinate = entity.getCoordinates();
    float longitude = coordinate.getCoordinates()[0];
    float latitude = coordinate.getCoordinates()[1];


//
//    int hashtagIndex = tweetText.indexOf("#");
//    int hashtagIndexEnd = tweetText.indexOf(" ",hashtagIndex);
//    String hashtagText = tweetText.substring(hashtagIndex+1,hashtagIndexEnd);
//    +"q"+ EQUAL+ "%23" +hashtagText+AMPERSAND

    uri = URI.create(
        API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + percentEscaper.escape(tweetText) +
            AMPERSAND + "long" + EQUAL + longitude +
            AMPERSAND + "lat" + EQUAL + latitude);

    //  System.out.println(uri);
    // Execute Http Request and gets Http Response from TwitterHttpHelper (it does auth as well)
    HttpResponse response = httpHelper.httpPost(uri);

    // valid response and convert the http response to Tweet object
    return parseResponseBody(response, HTTP_OK);
  }

  /**
   * Check response status code and convert response entity to tweet object
   *
   * @param response
   * @param expectedStatusCode
   * @return Tweet object
   */
  public Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
    Tweet tweet = null;

    // Check response status
    // Comparing actual status code to expected status code (200)
    int actualStatusCode = response.getStatusLine().getStatusCode();
    if (actualStatusCode != expectedStatusCode) {
      try {
        System.out.println(EntityUtils.toString(response.getEntity()));
      } catch (IOException e) {
        System.out.println("Response has no entity");
      }
      throw new RuntimeException("Unexpected HTTP status: " + actualStatusCode);
    }

    // No Response
    if (response.getEntity() == null) {
      throw new RuntimeException("Empty response body");
    }
    // Convert Response Entity to String
    String jsonString;
    try {
      jsonString = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      throw new RuntimeException("Unable to convert response entity to String. ", e);
    }

    // Deserialize JSON String to Tweet Object
    try {
      tweet = JsonUtil.toObjectFromJson(jsonString, Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("Unable to convert JSON string to Object");
    }
    return tweet;
  }


  /**
   * Find an entity(Tweet) by its id
   *
   * @param id entity id
   * @return Tweet entity
   */
  @Override
  public Tweet findById(String id) {
      /*
         GET https://api.twitter.com/1.1/statuses/show.json
         id : the numeric ID of the desired tweet
         example : GET https://api.twitter.com/1.1/statuses/show.json?id=210462857140252672
       */

    URI uri = URI.create(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + id);
    HttpResponse response = httpHelper.httpGet(uri);

    // valid response and convert the http response to Tweet object
    return parseResponseBody(response, HTTP_OK);
  }


  /**
   * Delete an entity(Tweet) by its ID
   *
   * @param id of the entity to be deleted
   * @return deleted entity
   */
  @Override
  public Tweet deleteById(String id)  {

    /*
     POST https://api.twitter.com/1.1/statuses/destroy/:id.json
    id : the numerical ID of the desired status
    example: POST https://api.twitter.com/1.1/statuses/destroy/240854986559455234.json
     */
    URI uri = URI.create(API_BASE_URI + DELETE_PATH + "/" + id +".json") ;
    // System.out.println(uri);
    HttpResponse response = null;
    try {
      response = httpHelper.httpPost(uri);
    } catch (OAuthMessageSignerException e) {
      throw new RuntimeException(e);
    } catch (OAuthExpectationFailedException e) {
      throw new RuntimeException(e);
    } catch (OAuthCommunicationException e) {
      throw new RuntimeException(e);
    }

    return parseResponseBody(response, HTTP_OK);
  }
}

