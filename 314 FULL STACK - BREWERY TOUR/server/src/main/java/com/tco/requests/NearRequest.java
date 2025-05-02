package com.tco.requests;

import com.tco.misc.GeographicLocations;
import com.tco.misc.Places;
import com.tco.misc.Place;
import com.tco.misc.Distances;
import com.tco.misc.CalculatorFactory;
import com.tco.misc.DistanceCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearRequest extends Request {

  public static final transient Logger log = LoggerFactory.getLogger(NearRequest.class);

  private Place place;
  private Integer distance;
  private Double earthRadius;
  private String formula;
  private Integer limit;
  private Places places;
  private Distances distances;

  public NearRequest(Place place, Integer distance, Double earthRadius, String formula, Integer limit) {
    this.place = place;
    this.distance = distance;
    this.earthRadius = earthRadius;
    this.formula = formula;
    this.limit = limit;
  }

  @Override
  public void buildResponse() {
    near();
    log.trace("buildResponse -> {}", this);
  }

  private void near() {
    try {
      GeographicLocations geographicLocations = new GeographicLocations();
      Places nearbyPlaces = geographicLocations.near(place, distance, earthRadius, formula, limit);
      makeDistances(nearbyPlaces);

      this.places = nearbyPlaces;
    } catch (Exception e) {
      log.error("error in near request");
    }
  }

  //distances
  private void makeDistances(Places nearbyPlaces) {
    this.distances = new Distances();
    if (!(nearbyPlaces.size() == 0)) {
      DistanceCalculator distanceCalculator = CalculatorFactory.get(formula);
      for (Place p : nearbyPlaces) {
        this.distances.add(distanceCalculator.between(this.place, p, this.earthRadius));
      }
    }
  }
}
