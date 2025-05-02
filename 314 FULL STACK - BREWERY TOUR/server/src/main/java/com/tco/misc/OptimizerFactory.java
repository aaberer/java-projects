package com.tco.misc;

public class OptimizerFactory {

  public TourConstruction get(long N, double response) {
    // n is number of places
    //if less than 50 milliseconds dont otpimize
    if (response <= 50) {
      return new NoOptimizer();
    } else if (N >= 1800 && response < 1200) {
      return new NoOptimizer();
    } else {
      return new OneOptimizer();
    }
  }
}
