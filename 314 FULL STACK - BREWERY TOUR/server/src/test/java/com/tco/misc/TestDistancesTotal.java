package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.tco.misc.Distances;


public class TestDistancesTotal {

  @Test
  @DisplayName("Test if distances empty list")
  public void emptyListTest() {
    Distances distances = new Distances();

    long result = distances.total();
    long expected = 0L;

    assertEquals(expected, result);
  }

  @Test
  @DisplayName("Test if distances returns correct sum")
  public void multipleElementsTest() {
    long num1 = 5L;
    long num2 = 10L;
    long num3 = 15L;

    Distances distances = new Distances();

    distances.add(num1);
    distances.add(num2);
    distances.add(num3);

    long result = distances.total();
    long expected = 30L;

    assertEquals(expected, result);
  }
}
