package com.tco.misc;

public abstract class CalculatorFactory {

  //factory method get
  public static DistanceCalculator get(String formula) {
    if (formula == null) {
      return new VincentyCalculator();
    }
    switch (formula.toLowerCase()) {
      case "haversine":
        return new HaversineCalculator();
      case "cosines":
        return new CosinesCalculator();
      case "vincenty":
        return new VincentyCalculator();
      default:
        return new VincentyCalculator();
    }
  }

  // Abstract method to create the calculator
  public abstract DistanceCalculator create();
}
