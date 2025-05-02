package com.tco.requests;

import com.tco.misc.OptimizerFactory;
import com.tco.misc.TourConstruction;


import com.tco.misc.Place;
import com.tco.misc.Places;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TourRequest extends Request {

  public static final transient Logger log = LoggerFactory.getLogger(TourRequest.class);

  private Places places;
  private final double earthRadius;
  private final String formula;
  private double response;


  // Testing not used during normal execution
  public TourRequest(double earthRadius, String formula, Places places, double response) {
    super();
    this.requestType = "tour";
    this.earthRadius = earthRadius;
    this.formula = formula;
    this.places = places;
    this.response = response;
  }

  @Override
  public void buildResponse() {
    this.places = buildTour();
    log.trace("buildResponse -> {}", this);
  }

  private Places buildTour() {
    //Log starting time for response time limiting
    long startTime = System.currentTimeMillis();

    // Default handling if no optimization needed
    if (this.places.size() <= 3) {
      return this.places;
    }

    //Creates computationTimeAvailable which has a time cushion that scales based on the number of places
    long timeCushion = (long) (this.places.size() / 10);
    long computationTimeAvailable = (long) ((this.response * 1000) - ((System.currentTimeMillis() - startTime)
        + timeCushion));

    OptimizerFactory optimizerFactory = new OptimizerFactory();
    TourConstruction optimizer = optimizerFactory.get(this.places.size(),
        (computationTimeAvailable - (System.currentTimeMillis() - startTime)));
    this.places = optimizer.construct(this.places, this.earthRadius, this.formula,
        (computationTimeAvailable - (System.currentTimeMillis() - startTime)));

    return this.places;
  }

  public Places getPlaces() {  // for testing purposes
    return this.places;
  }


}