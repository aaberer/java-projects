package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static java.lang.Math.toRadians;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;


public class TestGeographicLocations {
    private GeographicLocations geographicLocations;
    private Place mockPlace;
    private Place edgePlace;


    @BeforeEach
    public void setUp() {
        geographicLocations = new GeographicLocations();
        mockPlace = new Place();
        mockPlace.put("latitude", "40.7128");
        mockPlace.put("longitude", "-74.0060");
        edgePlace = new Place();
        edgePlace.put("latitude", "89.9");
        edgePlace.put("longitude", "179.9");
    }

    @Test
    @DisplayName("colin00")
    public void testBoundingBox() {
        double earthRadius = 6371.0;

        double[] boundingBoxCoords = geographicLocations.boundingBox(
                mockPlace.latRadians(), mockPlace.lonRadians(), earthRadius, 10);
        double[] edgeBounding = geographicLocations.boundingBox(
                edgePlace.latRadians(), edgePlace.lonRadians(), earthRadius, 10);

        assertNotNull(boundingBoxCoords);
        assertEquals(4, boundingBoxCoords.length);

    }

    @Test
    @DisplayName("colin00")
    public void testNearNoLimit() {
        double earthRadius = 6371.0;

        Places mockPlaces = new Places();
        Place place1 = new Place();
        place1.put("latitude", "40.730610");
        place1.put("longitude", "-73.935242");
        mockPlaces.add(place1);

        Place place2 = new Place();
        place2.put("latitude", "40.735610");
        place2.put("longitude", "-73.945242");
        mockPlaces.add(place2);

        SqlDatabase sqlDatabase = new SqlDatabase() {
            public Places near(int max, double lat1, double lon1, double lat2, double lon2) {
                return mockPlaces;
            }
        };

        Places resultPlaces = geographicLocations.near(mockPlace, 10, earthRadius, "vincenty", 1000);

        assertNotNull(resultPlaces);
    }

    @Test
    @DisplayName("colin00")
    public void testNearWithLimit() {
        double earthRadius = 6371.0;

        Places mockPlaces = new Places();
        Place place1 = new Place();
        place1.put("latitude", "40.730610");
        place1.put("longitude", "-73.935242");
        mockPlaces.add(place1);

        Place place2 = new Place();
        place2.put("latitude", "40.735610");
        place2.put("longitude", "-73.945242");
        mockPlaces.add(place2);

        Place place3 = new Place();
        place3.put("latitude", "40.750610");
        place3.put("longitude", "-73.965242");
        mockPlaces.add(place3);

        SqlDatabase sqlDatabase = new SqlDatabase() {
            public Places near(int max, double lat1, double lon1, double lat2, double lon2) {
                return mockPlaces;
            }
        };

        Places resultPlaces = geographicLocations.near(mockPlace, 10, earthRadius, "someFormula", 2);

        assertNotNull(resultPlaces);
    }

    @Test
    @DisplayName("colin00")
    public void testNearWithDistanceFilter() {
        double earthRadius = 6371.0;

        Places mockPlaces = new Places();
        Place place1 = new Place();
        place1.put("latitude", "40.730610");
        place1.put("longitude", "-73.935242");
        mockPlaces.add(place1);

        Place place2 = new Place();
        place2.put("latitude", "40.8128");
        place2.put("longitude", "-74.0060"); 
        mockPlaces.add(place2);

        SqlDatabase sqlDatabase = new SqlDatabase() {
            public Places near(int max, double lat1, double lon1, double lat2, double lon2) {
                return mockPlaces;
            }
        };

        Places resultPlaces = geographicLocations.near(mockPlace, 10, earthRadius, "someFormula", 10);

        assertNotNull(resultPlaces);
    }

    @Test
    @DisplayName("colin00")
    public void testBubbleSort() {
        double earthRadius = 6371.0;
        Places mockPlaces = new Places();
        Place place1 = new Place();
        place1.put("latitude", "40.730610");
        place1.put("longitude", "-73.935242");
        mockPlaces.add(place1);

        Place place2 = new Place();
        place2.put("latitude", "49.8128");
        place2.put("longitude", "-74.0060"); 
        mockPlaces.add(place2);

        DistanceCalculator d = CalculatorFactory.get("vincenty");
        Places resultPlaces = geographicLocations.bubbleSort(mockPlaces,place2,earthRadius,d);

        assertNotNull(resultPlaces);
    }

    @Test
    @DisplayName("wwardlow: Testing Bounding Box Left > Right")
    public void testLeftRight() {
        double earthRadius = 6371;
        double lat = 0;
        double lng = Math.toRadians(179);
        double radius = 500;

        double [] result = geographicLocations.boundingBox(lat, lng, earthRadius, radius);
        assertNotNull(result);
    }
    @Test
    @DisplayName("nlrohr: Validate Place Initialization")
    public void testPlaceInitialization() {
        Place testPlace = new Place();
        testPlace.put("latitude", "34.0522");
        testPlace.put("longitude", "-118.2437");

        assertNotNull(testPlace);
        assertEquals("34.0522", testPlace.get("latitude"));
        assertEquals("-118.2437", testPlace.get("longitude"));
    }
}
