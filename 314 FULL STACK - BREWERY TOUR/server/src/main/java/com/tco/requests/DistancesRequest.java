package com.tco.requests;

import com.tco.misc.CosinesCalculator;
import com.tco.misc.HaversineCalculator;
import com.tco.misc.CalculatorFactory;


import com.tco.misc.Place;
import com.tco.misc.Places;
import com.tco.misc.Distances;
import com.tco.misc.VincentyCalculator;
import com.tco.misc.DistanceCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistancesRequest extends Request {

  public static final transient Logger log = LoggerFactory.getLogger(DistancesRequest.class);

  private final Places places;
  private final double earthRadius;
  private String formula;
  private Distances distances;

  // Testing not used during normal execution
  public DistancesRequest(double earthRadius, String formula, Places places) {
    this.requestType = "distances";
    this.earthRadius = earthRadius;
    this.formula = formula;
    this.places = places;
  }

  @Override
  public void buildResponse() {
    distances = buildDistanceList();
    log.trace("buildResponse -> {}", this);
  }

  private Distances buildDistanceList() {
    this.distances = new Distances();
    Places placeList = this.places;

    // Default handling if not needed for calculation
    if (placeList.size() == 0) {
      return this.distances;
    }
    if (placeList.size() == 1) {
      this.distances.add(0L);
      return this.distances;
    }

    // Determine which formula to use
    calculateDistances(placeList);

    return this.distances;
  }

  // Updated to call the static between method based on formula
  private void calculateDistances(Places placeList) {
    for (int count = 0; count < placeList.size(); count++) {
      long distanceLong;
      if (count == placeList.size() - 1) {
        distanceLong = getDistanceBetween(placeList.get(count), placeList.get(0));
      } else if (!placeList.get(count).equals(placeList.get(count + 1))) {
        distanceLong = getDistanceBetween(placeList.get(count), placeList.get(count + 1));
      } else {
        distanceLong = 0L;
      }
      this.distances.add(distanceLong);
    }
  }

  // Helper method to use correct calculator based on the need
  private long getDistanceBetween(Place from, Place to) {
    DistanceCalculator calculator = CalculatorFactory.get(this.formula);
    return calculator.between(from, to, this.earthRadius);
  }
}
