package com.tco.requests;

import com.tco.misc.Place;
import com.tco.misc.Places;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTourRequest {

  private ConfigRequest conf;
  private TourRequest tour;

  private Places inputPlaces;
  private double radius;
  private String formula;
  private double response;

  @BeforeEach
  public void createConfigurationForTestsCases(){
    Place newYork = new Place();
    newYork.put("latitude", "40.7128");
    newYork.put("longitude", "-74.0060");

    Place losAngeles = new Place();
    losAngeles.put("latitude", "34.0522");
    losAngeles.put("longitude", "-118.2437");

    inputPlaces = new Places();
    inputPlaces.add(newYork);
    inputPlaces.add(losAngeles);

    radius = 100.0;
    formula = "cosines";
    response = 5000.0; // 5 seconds

    tour = new TourRequest(radius, formula, inputPlaces, response);
    tour.buildResponse();

    conf = new ConfigRequest();
    conf.buildResponse();
  }

  @Test
  @DisplayName("aaberer: Request type is \"tour\"")
  public void testType(){
    tour = new TourRequest(radius, formula, inputPlaces, response);
    tour.buildResponse();
    String type = tour.getRequestType();
    assertEquals("tour", type);
  }

  @Test
  @DisplayName("aaberer: Features includes \"tour\"")
  public void testFeaturesIncludes(){
    assertTrue(conf.validFeature("tour"));
  }

  @Test
  @DisplayName("aaberer: Tour returns places if size <= 3")
  public void testTourReturnsPlacesIfSizeLessThan3(){
    assertEquals(inputPlaces, tour.getPlaces());
  }

  @Test
  @DisplayName("aaberer: Tour returns if places >3")
  public void testTourReturnsPlacesIfPlacesGreaterThan3(){
    Place chicago = new Place();
    chicago.put("latitude", "41.8781");
    chicago.put("longitude", "-87.6298");

    Place miami = new Place();
    miami.put("latitude", "25.7617");
    miami.put("longitude", "-80.1918");

    inputPlaces.add(chicago);
    inputPlaces.add(miami);

    TourRequest tour2 = new TourRequest(radius, formula, inputPlaces, response);
    tour2.buildResponse();

    assertEquals(inputPlaces, tour2.getPlaces());
  }

}
