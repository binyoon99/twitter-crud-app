package com.binyoon.apps.twitter.model;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "created_at",
    "id",
    "id_str",
    "text",
    "entities",
    "coordinates",
    "retweet_count",
    "favorite_count",
    "favorited",
    "retweeted"

})

public class Tweet {

  @JsonProperty("created_at")
  private String createdAt ;
  @JsonProperty("id")
  private long id;
  @JsonProperty("id_str")
  private String idStr;
  @JsonProperty("text")
  private String text;
  @JsonProperty("entities")
  private Entities entities;
  @JsonProperty("coordinates")
  private Coordinates coordinates;
  @JsonProperty("retweet_count")
  private int retweetCount;
  @JsonProperty("favorite_count")
  private Integer favCount;
  @JsonProperty("favorited")
  private Boolean favorited;
  @JsonProperty("retweeted")
  private Boolean retweeted;

  @JsonGetter
  public String getCreatedAt() {
    return createdAt;
  }
  @JsonSetter
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
  @JsonGetter
  public long getId() {
    return id;
  }
  @JsonSetter
  public void setId(long id) {
    this.id = id;
  }
  @JsonGetter
  public String getIdStr() {
    return idStr;
  }
  @JsonSetter
  public void setIdStr(String idStr) {
    this.idStr = idStr;
  }
  @JsonGetter
  public String getText() {
    return text;
  }
  @JsonSetter
  public void setText(String text) {
    this.text = text;
  }
  @JsonGetter
  public Entities getEntities() {
    return entities;
  }
  @JsonSetter
  public void setEntities(Entities entities) {
    this.entities = entities;
  }
  @JsonGetter
  public Coordinates getCoordinates() {
    return coordinates;
  }
  @JsonSetter
  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }
  @JsonGetter
  public int getRetweetCount() {
    return retweetCount;
  }
  @JsonSetter
  public void setRetweetCount(int retweetCount) {
    this.retweetCount = retweetCount;
  }
  @JsonGetter
  public Integer getFavCount() {
    return favCount;
  }
  @JsonSetter
  public void setFavCount(Integer favCount) {
    this.favCount = favCount;
  }
  @JsonGetter
  public Boolean getFavorited() {
    return favorited;
  }
  @JsonSetter
  public void setFavorited(Boolean favorited) {
    this.favorited = favorited;
  }
  @JsonGetter
  public Boolean getRetweeted() {
    return retweeted;
  }
  @JsonSetter
  public void setRetweeted(Boolean retweeted) {
    this.retweeted = retweeted;
  }
}