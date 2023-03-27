package com.binyoon.apps.twitter.utility;

import com.binyoon.apps.twitter.model.Coordinates;
import com.binyoon.apps.twitter.model.Entities;
import com.binyoon.apps.twitter.model.Hashtag;
import com.binyoon.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;

// TweetUtil creates tweet entity with given text, longitude, and latitude
public class TweetUtil {

  public static Tweet buildTweet(String text, float longitude, float latitude) {
    var tweet = new Tweet();
    Coordinates coordinates = new Coordinates();
    Hashtag hashtag = new Hashtag();
    Entities entities = new Entities();

//
//    int hashtagIndex = text.indexOf("#");
//    int hashtagIndexEnd = text.indexOf(" ",hashtagIndex);
//    hashtag.setIndices(new int []{hashtagIndex, hashtagIndexEnd});
//    hashtag.setText(text.substring(hashtagIndex+1,hashtagIndexEnd));
//    List<Hashtag> list = new ArrayList<>();
//    list.add(hashtag);
//    entities.setHashtags(list);

    // set instance of Coordinate
    float[] coordinateArray = {longitude, latitude};

    coordinates.setCoordinates(coordinateArray);
    coordinates.setType("Point");

    // set instance of Hashtag and set Entities\

    // set text and instance of coordinate to tweet attribute
    tweet.setText(text);
    tweet.setCoordinates(coordinates);
    tweet.setEntities(entities);
    return tweet;
  }
  }