package com.tco.misc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

public class TestNoOptimizer {

  private NoOptimizer noOptimizer;
  private Places inputPlaces;

  @BeforeEach
  public void setUp() {
    noOptimizer = new NoOptimizer();

    Place newYork = new Place();
    newYork.put("latitude", "40.7128");
    newYork.put("longitude", "-74.0060");

    Place losAngeles = new Place();
    losAngeles.put("latitude", "34.0522");
    losAngeles.put("longitude", "-118.2437");

    inputPlaces = new Places();
    inputPlaces.add(newYork);
    inputPlaces.add(losAngeles);
  }

  @Test
  @DisplayName("aaberer: Can create object of class test")
  public void testNoOptimizerClassExists() {
    assertNotNull(noOptimizer, "NoOptimizer should make an object");
  }

  @Test
  @DisplayName("aaberer: Test instantiation")
  public void testNoOptimizerIsSubclassOfTourConstruction() {
    assertTrue(noOptimizer instanceof TourConstruction, "Subclass of TourConstruction.");
  }

  @Test
  @DisplayName("aaberer: Constructor test")
  public void testConstructReturnsSamePlaces() {
    double radius = 10.0;
    String formula = "cosines";
    double response = 0.0;

    Places result = noOptimizer.construct(inputPlaces, radius, formula, response);
    assertSame(inputPlaces, result, "Returns same places");
  }

  @Test
  @DisplayName("aaberer: Call improve, no EX") // change when its implemented
  public void testImproveDoesNotThrow() {
    assertDoesNotThrow(() -> noOptimizer.improve(), "No exception thrown");
  }

  @Test
  @DisplayName("aknott: Construct with empty Places")
  public void testConstructWithEmptyPlaces() {
      Places emptyPlaces = new Places();
      Places result = noOptimizer.construct(emptyPlaces, 10.0, "cosines", 0.0);
      assertSame(emptyPlaces, result);
      assertEquals(0, result.size());
  }

  @Test
  @DisplayName("aknott: Improve does not modify Places")
  public void testImproveDoesNotModifyPlaces() {
      int initialSize = inputPlaces.size();
      noOptimizer.improve();
      assertEquals(initialSize, inputPlaces.size());
  }
  @Test
  @DisplayName("aknott: Test construct method returns input places without modification")
  public void testConstructReturnsInputPlaces() {
      Places result = noOptimizer.construct(inputPlaces, 100, "Cosines", 5000);
      assertSame(inputPlaces, result);
  }

}

