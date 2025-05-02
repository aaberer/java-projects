package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestPlace {

  @Test
  @DisplayName("aaberer:Test if place lat conversion")
  public void latPlaceConversionTest() {
    Place bogota = new Place();
    bogota.put("latitude", "4.719339513562428");
    bogota.put("longitude", "-73.87465962748392");
    double expected = Math.toRadians(4.719339513562428);

    assertEquals(expected, bogota.latRadians());
  }

  @Test
  @DisplayName("aaberer:Test if place lon conversion")
  public void lonPlaceConversionTest() {
    Place bogota = new Place();
    bogota.put("latitude", "4.719339513562428");
    bogota.put("longitude", "-73.87465962748392");
    double expected = Math.toRadians(-73.87465962748392);

    assertEquals(expected, bogota.lonRadians());
  }

}
