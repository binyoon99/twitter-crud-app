package com.binyoon.apps.twitter.model;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "text",
    "indices"
})

public class Hashtag {
  @JsonProperty("text")
  private String text;

  @JsonProperty("indices")
  private int [] indices;
  @JsonGetter
  public String getText() {
    return text;
  }
  @JsonSetter
  public void setText(String text) {
    this.text = text;
  }
  @JsonGetter
  public int[] getIndices() {
    return indices;
  }
  @JsonSetter
  public void setIndices(int[] indices) {
    this.indices = indices;
  }
}
