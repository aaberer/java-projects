package com.tco.misc;

public class HaversineCalculator implements DistanceCalculator {

  @Override
  public long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
    // Get the difference (delta) in latitude and longitude radians
    double deltaLat = from.latRadians() - to.latRadians();
    double deltaLon = from.lonRadians() - to.lonRadians();

    // Calculate the average of the latitudes
    double avgLat = (from.latRadians() + to.latRadians()) / 2.0;

    // First term: sin^2(Δφ / 2)
    double term1 = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2);

    // Second term: cos^2((φ1 + φ2) / 2) - sin^2(Δφ / 2) * sin^2(Δλ / 2)
    double term2 = (Math.cos(avgLat) * Math.cos(avgLat) - term1) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

    // Calculate the final result inside the square root
    double a = term1 + term2;

    // Haversine distance: Δσ = 2 * arcsin(sqrt(a))
    double c = 2 * Math.asin(Math.sqrt(a));

    // Return the distance (abs for positive radius)
    return Math.round(Math.abs(earthRadius) * c);
  }

}