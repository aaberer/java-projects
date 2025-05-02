package com.tco.misc;

public class NoOptimizer extends TourConstruction {

  @Override
  public Places construct(Places places, double radius, String formula, double response) {
    return places;
  }

  @Override
  public void improve() {
    // No improvement logic
  }
}