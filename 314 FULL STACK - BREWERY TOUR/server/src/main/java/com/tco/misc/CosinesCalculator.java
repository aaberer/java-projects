package com.tco.misc;

public class CosinesCalculator implements DistanceCalculator {

  @Override
  public long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {

    double lat1 = from.latRadians();
    double lon1 = from.lonRadians();
    double lat2 = to.latRadians();
    double lon2 = to.lonRadians();

    //Calculate distance using Cosine formula
    double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2) +
        Math.cos(lat1) * Math.cos(lat2) * Math.cos(Math.abs(lon1 - lon2)));

    //Multiply by earthRadius
    long distance = Math.round(earthRadius * angle);

    return distance;

  }

}
