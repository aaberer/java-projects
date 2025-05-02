package com.tco.misc;

import java.util.LinkedHashMap;
import com.tco.misc.GeographicCoordinate;


public class Place extends LinkedHashMap<String,String> implements GeographicCoordinate {
  //Empty constructor required for the GSON request parser
  public Place() {
  }

  @Override
  public Double latRadians() {
    return Math.toRadians(Double.parseDouble(this.get("latitude")));
  }

  @Override
  public Double lonRadians() {
    return Math.toRadians(Double.parseDouble(this.get("longitude")));
  }
}