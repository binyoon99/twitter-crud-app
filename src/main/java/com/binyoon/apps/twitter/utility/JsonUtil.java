package com.binyoon.apps.twitter.utility;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

// To see example, please go to package com.binyoon.apps.twitter.example
public class JsonUtil {

  /**
   * Convert a java object to JSON String
   * @param object input object
   * @param prettyJson boolean
   * @param includeNullValues
   * @return JSON string
   * @throws com.fasterxml.jackson.core.JsonProcessingException
   */
  public static String toJson(Object object, boolean prettyJson, boolean includeNullValues)
      throws JsonProcessingException {
    ObjectMapper m = new ObjectMapper ();

    // no null values
    if (!includeNullValues){
      m.setSerializationInclusion(Include.NON_NULL);
    }
    if (prettyJson){
      m.enable(SerializationFeature.INDENT_OUTPUT);
    }
    return m.writeValueAsString(object);
  }

  /**
   * Parse JSON string to an object.  generic object.
   * @param json JSON string
   * @param clazz the return object's class
   * @return
   * @param <T> type generic object.
   * @throws IOException
   */
  public static <T> T toObjectFromJson(String json, Class clazz) throws IOException{
    ObjectMapper m = new ObjectMapper();
    return (T) m.readValue(json,clazz);
  }
}
