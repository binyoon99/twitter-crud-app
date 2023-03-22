package com.binyoon.apps.twitter.example;

import com.binyoon.apps.twitter.example.dto.Company;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

public class JsonParser {

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

  public static void main(String[] args) throws IOException {

    // parsing json companyStr => Company.class' object
    Company company = toObjectFromJson(companyStr, Company.class);

    // parsing ^Company company object => string
    System.out.println(toJson(company, true, false));
  }

  public static final String companyStr = "{\n"
      + "   \"symbol\":\"AAPL\",\n"
      + "   \"companyName\":\"Apple Inc.\",\n"
      + "   \"exchange\":\"Nasdaq Global Select\",\n"
      + "   \"description\":\"Apple Inc is designs, manufactures and markets mobile communication and media devices and personal computers, and sells a variety of related software, services, accessories, networking solutions and third-party digital content and applications.\",\n"
      + "   \"CEO\":\"Timothy D. Cook\",\n"
      + "   \"sector\":\"Technology\",\n"
      + "   \"financials\":[\n"
      + "      {\n"
      + "         \"reportDate\":\"2018-12-31\",\n"
      + "         \"grossProfit\":32031000000,\n"
      + "         \"costOfRevenue\":52279000000,\n"
      + "         \"operatingRevenue\":84310000000,\n"
      + "         \"totalRevenue\":84310000000,\n"
      + "         \"operatingIncome\":23346000000,\n"
      + "         \"netIncome\":19965000000\n"
      + "      },\n"
      + "      {\n"
      + "         \"reportDate\":\"2018-09-30\",\n"
      + "         \"grossProfit\":24084000000,\n"
      + "         \"costOfRevenue\":38816000000,\n"
      + "         \"operatingRevenue\":62900000000,\n"
      + "         \"totalRevenue\":62900000000,\n"
      + "         \"operatingIncome\":16118000000,\n"
      + "         \"netIncome\":14125000000\n"
      + "      }\n"
      + "   ],\n"
      + "   \"dividends\":[\n"
      + "      {\n"
      + "         \"exDate\":\"2018-02-09\",\n"
      + "         \"paymentDate\":\"2018-02-15\",\n"
      + "         \"recordDate\":\"2018-02-12\",\n"
      + "         \"declaredDate\":\"2018-02-01\",\n"
      + "         \"amount\":0.63\n"
      + "      },\n"
      + "      {\n"
      + "         \"exDate\":\"2017-11-10\",\n"
      + "         \"paymentDate\":\"2017-11-16\",\n"
      + "         \"recordDate\":\"2017-11-13\",\n"
      + "         \"declaredDate\":\"2017-11-02\",\n"
      + "         \"amount\":0.63\n"
      + "      }\n"
      + "   ]\n"
      + "}";
}
