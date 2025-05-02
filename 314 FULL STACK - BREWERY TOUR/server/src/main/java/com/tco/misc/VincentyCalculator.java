package com.tco.misc;

public class VincentyCalculator implements DistanceCalculator {

  @Override
  public long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
    //get radian coordinates
    //(theta is long)
    double fromLat = from.latRadians();
    double toLat = to.latRadians();
    double fromLong = from.lonRadians();
    double toLong = to.lonRadians();

    //make variables of commonly used operations to increase readability
    double sinFromLat = Math.sin(fromLat);
    double sinToLat = Math.sin(toLat);
    double cosFromLat = Math.cos(fromLat);
    double cosToLat = Math.cos(toLat);
    double deltaLong = Math.abs(fromLong - toLong);

    //Run Vincenty math based on desmos formula given in slack
    double a = (sinFromLat * sinToLat + cosFromLat * cosToLat * Math.cos(deltaLong));
    double b = Math.sqrt(Math.pow(cosToLat * Math.sin(deltaLong), 2) +
        Math.pow(cosFromLat * sinToLat - sinFromLat * cosToLat * Math.cos(deltaLong), 2));

    double c = Math.atan2(b, a);
    //round number of c * earth radius then get absolute value of it, and convert to long.
    long distance = Math.round(earthRadius * c);
    return distance;
  }

}
