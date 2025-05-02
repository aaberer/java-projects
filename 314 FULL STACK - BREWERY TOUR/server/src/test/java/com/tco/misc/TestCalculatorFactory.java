package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TestCalculatorFactory {

  @Test
  @DisplayName("aaberer: Switch case haversine")
  public void testGetHaversineCalculator() {
    DistanceCalculator calculator = CalculatorFactory.get("haversine");
    assertNotNull(calculator);
    assertTrue(calculator instanceof HaversineCalculator);
  }

  @Test
  @DisplayName("aaberer: Switch case cosines")
  public void testGetCosinesCalculator() {
    DistanceCalculator calculator = CalculatorFactory.get("cosines");
    assertNotNull(calculator);
    assertTrue(calculator instanceof CosinesCalculator);
  }

  @Test
  @DisplayName("aaberer: Switch case vincenty")
  public void testGetVincentyCalculator() {
    DistanceCalculator calculator = CalculatorFactory.get("vincenty");
    assertNotNull(calculator);
    assertTrue(calculator instanceof VincentyCalculator);
  }

  @Test
  @DisplayName("aaberer: Switch case default - vincenty")
  public void testGetDefaultCalculatorWithUnknownFormula() {
    DistanceCalculator calculator = CalculatorFactory.get("unknown");
    assertNotNull(calculator);
    assertTrue(calculator instanceof VincentyCalculator);
  }

  @Test
  @DisplayName("aaberer: Null formula - vincenty")
  public void testGetDefaultCalculatorWithNullFormula() {
    DistanceCalculator calculator = CalculatorFactory.get(null);
    assertNotNull(calculator);
    assertTrue(calculator instanceof VincentyCalculator);
  }
  @Test
  @DisplayName("nlrohr: Case insensitivity - haversine")
  public void testGetHaversineCalculatorCaseInsensitive() {
      DistanceCalculator calculator = CalculatorFactory.get("HaVeRsInE");
      assertNotNull(calculator);
      assertTrue(calculator instanceof HaversineCalculator);
  }
}
