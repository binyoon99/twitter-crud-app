package com.binyoon.apps.twitter.dao;

import com.binyoon.apps.twitter.dao.helper.HttpHelper;
import com.binyoon.apps.twitter.model.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TwitterDao implements CrdDao<Tweet, String>{

  //URI constants
  private static final String API_BASE_URI="https://api.twitter.com";
  private static final String POST_PATH="/1.1/statuses/update.json";
  private static final String SHOW_PATH="/1.1/statuses/show.json";
  private static final String DELETE_PATH="/1.1/statuses/destroy";
  //URI symbols
  private static final String QUERY_SYM="?";
  private static final String AMPERSAND="&";
  private static final String EQUAL="=";

  //Response code
  private static final int HTTP_OK=200;

  private HttpHelper httpHelper;

  final Logger logger = LoggerFactory.getLogger(TwitterDao.class);
  /**
   * Create an entity(Tweet) to the underlying storage
   * @param entity entity that to be created
   * @return created entity
   */
  @Override
  public Tweet create(Tweet entity) {
    return null;
  }

  /**
   * Find an entity(Tweet) by its id
   * @param id entity id
   * @return Tweet entity
   */
  @Override
  public Tweet findById(String id) {
    return null;
  }
  /**
   * Delete an entity(Tweet) by its ID
   * @param id of the entity to be deleted
   * @return deleted entity
   */
  @Override
  public Tweet deleteById(String id) {
    return null;
  }
}
