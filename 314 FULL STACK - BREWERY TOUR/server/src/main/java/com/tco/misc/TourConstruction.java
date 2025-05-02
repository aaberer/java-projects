package com.tco.misc;

import java.util.List;
import java.util.Random;

public abstract class TourConstruction {

  // Subclasses to implement tour construction logic
  public abstract Places construct(Places places, double radius, String formula, double response);

  // Subclasses to implement optimization logic
  public abstract void improve();

  /**
   * Chooses a random starting place from the given list of places.
   *
   * @param places The list of places to choose from.
   * @return The random starting place.
   */
  public Place chooseRandomStartingPlace(Places places) {
    if (places == null || places.isEmpty()) {
      throw new IllegalArgumentException("Places list cannot be null or empty");
    }
    Random random = new Random();
    int randomIndex = random.nextInt(places.size());
    return places.get(randomIndex);
  }
}
