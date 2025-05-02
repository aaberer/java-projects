package com.tco.misc;

public class OneOptimizer extends TourConstruction {

  private double radius;
  private String formula;
  private double response;
  private Places places;
  private boolean[] visitedCities;
  private long[][] placeDistanceMatrix;
  private int[] tour;
  private int numPlaces;

  @Override
  public Places construct(Places places, double radius, String formula, double response) {
    this.radius = radius;
    this.formula = formula;
    this.response = response;
    this.places = places;
    this.numPlaces = places.size();
    this.visitedCities = new boolean[this.numPlaces];
    this.tour = new int[this.numPlaces];

    this.placeDistanceMatrix = new long[this.numPlaces][this.numPlaces];

    //If not able to fully complete the functions
    if (!nearestNeighbor()) {
      return this.places;
    }

    //check new tour longer or nah
    if (newTourLonger()) {
      return this.places;
    } else {
      reArrangePlaces();
      return this.places;
    }
  }

  @Override
  public void improve() {
  }

  public boolean newTourLonger() {
    int[] baseTour = new int[this.numPlaces];

      for (int i = 0; i < this.numPlaces; i++) {
          baseTour[i] = i;
      }

    if (calcTourDist(this.tour) > calcTourDist(baseTour)) {
      return true;
    }
    return false;
  }

  public boolean newTourLonger(int[] baseTour, long[][] placeDistanceMatrix) { // for testing
    this.placeDistanceMatrix = placeDistanceMatrix;
    return calcTourDist(this.tour) > calcTourDist(baseTour);
  }

  private long calcTourDist(int[] tour) {
    long totalDistance = 0;
    // Loop through each city in the tour
    for (int i = 0; i < tour.length - 1; i++) {
      int fromCity = tour[i];
      int toCity = tour[i + 1];
      totalDistance += this.placeDistanceMatrix[fromCity][toCity];
    }
    // Add the distance from the last city back to the first city to complete the tour
    totalDistance += this.placeDistanceMatrix[tour[tour.length - 1]][tour[0]];
    return totalDistance;
  }


  private void reArrangePlaces() {
    Places temp = new Places();
    for (int i = 0; i < this.tour.length; i++) {
      temp.add(this.places.get(tour[i]));
    }
    this.places = temp;
  }

  public boolean makeDistMatrix(long startTime) {
    DistanceCalculator distanceCalculator = CalculatorFactory.get(this.formula);
    for (int i = 0; i < this.numPlaces; i++) {
      this.tour[i] = i;
      for (int j = 0; j < this.numPlaces; j++) {
        //Making the matrix can take longer than specifed for long place lists, try to do in time but if not reuturn with no optimization at all=
        if ((System.currentTimeMillis() - startTime) > this.response) {
          return false;
        }
        if (i != j) {
          this.placeDistanceMatrix[i][j] = distanceCalculator.between(places.get(i), places.get(j), this.radius);
        }
      }
    }
    return true;
  }

  public boolean nearestNeighbor() {
    long startTime = System.currentTimeMillis();
    if (!makeDistMatrix(startTime)) {
      return false;
    }

    // Start from the first city
    int currentCity = 0;
    //visited cities bool
    this.visitedCities[currentCity] = true;

    // Visit all cities in the tour, one by one
    for (int i = 1; i < this.numPlaces; i++) {
      int nearestCity = -1;
      long nearestDistance = Long.MAX_VALUE;
      // Find the nearest unvisited city

      for (int j = 0; j < this.numPlaces; j++) {
        //check to not go over time
        if ((System.currentTimeMillis() - startTime) >= this.response) {
          return false;
        }
        //if city not visited, and distance less than previous best, and cities not same
        if (!visitedCities[j] && this.placeDistanceMatrix[currentCity][j] < nearestDistance && j != i) {
          nearestDistance = this.placeDistanceMatrix[currentCity][j];
          nearestCity = j;
        }
      }
      // Visit the nearest city
      if (nearestCity != -1) {
        visitedCities[nearestCity] = true;
        currentCity = nearestCity; // Move to the next city
        this.tour[i] = nearestCity;
      }

    }
    return true;
  }


  public int[] getTour() {  // for testing purposes
    return tour;
  }

  public void setTour(int[] tour) {  // for testing purposes
    this.tour = tour;
  }

  public long[][] getDistanceMatrix() {  // for testing purposes
    return placeDistanceMatrix;
  }

  public void setDistanceMatrix(long[][] placeDistanceMatrix) {  // for testing purposes
    this.placeDistanceMatrix = placeDistanceMatrix;
  }

  public Places getPlaces() {  // for testing purposes
    return this.places;
  }

  public double getRadius() {  // for testing purposes
    return this.radius;
  }

  public String getFormula() {  // for testing purposes
    return this.formula;
  }

  public double getResponse() {  // for testing purposes
    return this.response;
  }

  public int getNumPlaces() {  // for testing purposes
    return numPlaces;
  }

  public boolean[] getVisitedCities() {  // for testing purposes
    return visitedCities;
  }

}