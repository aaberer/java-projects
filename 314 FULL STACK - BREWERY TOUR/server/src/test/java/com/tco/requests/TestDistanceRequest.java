package com.tco.requests;

import com.tco.misc.Distances;
import com.tco.misc.Place;
import com.tco.misc.Places;
import java.lang.Math;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestDistanceRequest {
  Places places;
  Place p;

  @BeforeEach
  public void beforeEach(){
    places = new Places();
    p = new Place();
    p.put("latitude", "10");
    p.put("longitude", "10");
  }

  @Test
  @DisplayName("colin00:Test Empty Places")
  public void testEmptyPlaces(){
    DistancesRequest request = new DistancesRequest(1000, "vincenty", places);
    request.buildResponse();
  }

  @Test
  @DisplayName("colin00:Test One Place")
  public void testOnePlace(){
    places.add(p);
    DistancesRequest request = new DistancesRequest(1000, "vincenty", places);
    request.buildResponse();
  }

  @Test
  @DisplayName("colin00:Test Two Places")
  public void testTwoPlace(){
    places.add(p);
    places.add(p);
    DistancesRequest request = new DistancesRequest(1000, "vincenty", places);
    request.buildResponse();
  }

  @Test
  @DisplayName("colin00:Test Cosines Formula")
  public void testCosines(){
    places.add(p);
    places.add(p);
    DistancesRequest request = new DistancesRequest(1000, "cosines", places);
    request.buildResponse();
  }


  @Test
  @DisplayName("colin00:Test Haversines Formula")
  public void testHaversine(){
    places.add(p);
    places.add(p);
    DistancesRequest request = new DistancesRequest(1000, "haversine", places);
    request.buildResponse();
  }


  @Test
  @DisplayName("colin00:Test Null Formula")
  public void testNull(){
    places.add(p);
    places.add(p);
    DistancesRequest request = new DistancesRequest(1000, null, places);
    request.buildResponse();
  }

  @Test
  @DisplayName("colin00:Test Nonexist Formula")
  public void testNonexist(){
    places.add(p);
    places.add(p);
    DistancesRequest request = new DistancesRequest(1000, "not real", places);
    request.buildResponse();
  }
}
