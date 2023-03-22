package com.binyoon.apps.twitter.model;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "hashtags",
    "user_mentions"
})
public class Entities {

  @JsonProperty("hashtags")
  private List<Hashtag> hashtags;
  @JsonProperty("user_mentions")
  public List<UserMention> userMentions;

  @JsonGetter
  public List<Hashtag> getHashtags() {
    return hashtags;
  }

  @JsonSetter
  public void setHashtags(List<Hashtag> hashtags) {
    this.hashtags = hashtags;
  }

  @JsonGetter
  public List<UserMention> getUserMentions() {
    return userMentions;
  }

  @JsonSetter
  public void setUserMentions(List<UserMention> userMentions) {
    this.userMentions = userMentions;
  }
}