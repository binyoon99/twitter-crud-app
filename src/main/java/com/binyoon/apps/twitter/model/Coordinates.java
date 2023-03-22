package com.binyoon.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "coordinates",
    "type"
})
public class Coordinates {

  @JsonProperty("coordinates")
  public float[] coordinates;
  @JsonProperty("type")
  public String type;

  @JsonGetter
  public float[] getCoordinates() {
    return coordinates;
  }

  @JsonSetter
  public void setCoordinates(float[] coordinates) {
    this.coordinates = coordinates;
  }

  @JsonGetter
  public String getType() {
    return type;
  }

  @JsonSetter
  public void setType(String type) {
    this.type = type;
  }
}
