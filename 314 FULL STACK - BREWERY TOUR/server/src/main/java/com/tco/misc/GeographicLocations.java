package com.tco.misc;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.misc.Place;
import com.tco.misc.SqlDatabase;
import com.tco.misc.CalculatorFactory;


public class GeographicLocations {

  public static final transient Logger log = LoggerFactory.getLogger(GeographicLocations.class);

  public Places near(Place place, Integer distance, Double earthRadius, String formula, Integer limit) {
    try {
      DistanceCalculator calculator = CalculatorFactory.get(formula);

      SqlDatabase sqlDatabase = new SqlDatabase();

      double[] boundingBoxCoords = boundingBox(place.latRadians(), place.lonRadians(), earthRadius, distance);

      //set the limit and avoid going over as specced in the product specs
      Integer defaultLimit = limit;
      if (limit > 200 || limit == null) {
        defaultLimit = 200;
      }

      //the reason limit is 30k is because you might have a larger amt in the box before doing radius
      Places resultPlaces = sqlDatabase.near(100000, boundingBoxCoords[0], boundingBoxCoords[1], boundingBoxCoords[2],
          boundingBoxCoords[3]);
      Places outPlaces = new Places();

      //check if within tthe radius
      for (Place currentPlace : resultPlaces) {
        if (calculator.between(place, currentPlace, earthRadius) <= distance) {
          outPlaces.add(currentPlace);
        }
      }
      outPlaces = bubbleSort(outPlaces, place, earthRadius, calculator);
      if (limit < outPlaces.size()) {
        Places trimmedPlaces = new Places();
        for (int i = 0; i < limit; i++) {
          trimmedPlaces.add(outPlaces.get(i));
        }
        return trimmedPlaces;
      }

      return outPlaces;


    } catch (Exception e) {
      log.error("Error performing near request");
    }
    return new Places(); // defualt if try/catch does not hit
  }

  public Places bubbleSort(Places placeList, Place centerPlace, Double earthRadius, DistanceCalculator calculator) {
    int n = placeList.size();
    boolean swapped;

    for (int i = 0; i < n - 1; i++) {
      swapped = false;

      for (int j = 0; j < n - 1 - i; j++) {
        long jDistance = calculator.between(centerPlace, placeList.get(j), earthRadius);
        long jOneDistance = calculator.between(centerPlace, placeList.get(j + 1), earthRadius);
        if (jDistance > jOneDistance) {
          // Swap arr[j] and arr[j + 1]
          Place temp = placeList.get(j);
          placeList.set(j, placeList.get(j + 1));
          placeList.set(j + 1, temp);
          swapped = true;
        }
      }

      // If no two elements were swapped in the inner loop, break
      if (!swapped) {
        break;
      }
    }
    return placeList;
  }

  public double[] boundingBox(double latRad, double lngRad, double earthRadius, double radius) {
    radius = radius + 2;
    double latDelta = radius / earthRadius;
    double lngDelta = radius / (earthRadius * Math.cos(latRad));

    double topLat = latRad + latDelta;
    double bottomLat = latRad - latDelta;
    double leftLng = lngRad - lngDelta;
    double rightLng = lngRad + lngDelta;

    //hacky  way to wrap around, gross need to fix 
    if (bottomLat < Math.toRadians(-90) || topLat > Math.toRadians(90)) {
      bottomLat = Math.toRadians(-92);
      topLat = Math.toRadians(92);
    }
    if (leftLng < Math.toRadians(-180) || rightLng > Math.toRadians(180)) {
      leftLng = Math.toRadians(-182);
      rightLng = Math.toRadians(182);
    }

    if (topLat > bottomLat) {
      double temp = topLat;
      topLat = bottomLat;
      bottomLat = temp;
    }
    if (leftLng > rightLng) {
      double temp = leftLng;
      leftLng = rightLng;
      rightLng = temp;
    }
    return new double[]{Math.toDegrees(topLat), Math.toDegrees(bottomLat), Math.toDegrees(leftLng),
        Math.toDegrees(rightLng)};
  }

}