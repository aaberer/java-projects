package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static java.lang.Math.toRadians;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCosinesCalculator {
    private CosinesCalculator cosinesCalc;

    class geo implements GeographicCoordinate {
        private double dLat;
        private double dLon;
        public geo(Double lat, Double lon) {
            this.dLat = lat;
            this.dLon = lon;
        }
        public Double latRadians() { 
            return toRadians(dLat); 
        } 
        public Double lonRadians() { 
            return toRadians(dLon); 
        }
    }

    private long calculateDistance(double lat1, double lon1, double lat2, double lon2, double earthRadius) {
        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2) + 
                                 Math.cos(lat1) * Math.cos(lat2) * Math.cos(Math.abs(lon1 - lon2)));
        return Math.round(earthRadius * angle);
    }

    @BeforeEach
    public void setup(){
        cosinesCalc = new CosinesCalculator();
    }

    @Test
    public void testBetweenWithoutGeographicCoordinate() {
        double lat1 = Math.toRadians(40.7128);
        double lon1 = Math.toRadians(-74.0060);
        double lat2 = Math.toRadians(34.0522);
        double lon2 = Math.toRadians(-118.2437);
        double earthRadius = 6371.0;

        long distance = calculateDistance(lat1, lon1, lat2, lon2, earthRadius);
        long expectedDistance = 3940;

        assertEquals(expectedDistance, distance, 50);
    }

    @Test
    @DisplayName("aknott: Test distance between two identical points")
    public void testSameLocation() {
        final geo FromPlace = new geo(51.5074, -0.1278);
        final geo ToPlace = new geo(51.5074, -0.1278);
        long distance = cosinesCalc.between(FromPlace, ToPlace, 6371); 
        assertEquals(0, distance);
    }

    @Test
    @DisplayName("aknott: Test Cosines basic distance")
    public void basicDistance() {
        final geo FromPlace = new geo(45.504061, -73.573189);
        final geo ToPlace = new geo(-51.624784, -69.213440);
        long distance = cosinesCalc.between(FromPlace, ToPlace, 6371);
        assertEquals(10808, distance);
    }

    @Test
    @DisplayName("aknott:Test distance between Equator Points Cosines")
    public void testEquatorPoints() {
        final geo FromPlace = new geo(0.0, 0.0);
        final geo ToPlace = new geo(0.0, 90.0);
        long distance = cosinesCalc.between(FromPlace, ToPlace, 6371000); 
        assertEquals(10007543, distance);
    }

    @Test
    @DisplayName("aknott:Test distance between North Pole and Equator Cosines")
    public void testPoleToEquator() {
        final geo FromPlace = new geo(90.0, 0.0);
        final geo ToPlace = new geo(0.0, 0.0);
        long distance = cosinesCalc.between(FromPlace, ToPlace, 6371000); 
        assertEquals(10007543, distance);
    }

    @Test
    @DisplayName("aaberer: Test distance at the edge of floating-point precision")
    public void testPrecisionEdge() {
        final geo FromPlace = new geo(0.0, 0.0);
        final geo ToPlace = new geo(0.0, 1e-10);
        long distance = cosinesCalc.between(FromPlace, ToPlace, 6371);
        assertTrue(distance < 1, "Distance <= 1 km for extremely small difference.");
    }

    @Test
    @DisplayName("aaberer: Test distance with radius = 1m")
    public void testOneMeterRadius() {
        final geo FromPlace = new geo(0.0, 0.0);
        final geo ToPlace = new geo(0.0, 1.0);
        long distance = cosinesCalc.between(FromPlace, ToPlace, 1);
        assertEquals(0, distance, "Distance should be 0 when radius is 1 meter and angle is small.");
    }

    @Test
    @DisplayName("aaberer: Test distance with radius of zero")
    public void testZeroRadius() {
        final geo FromPlace = new geo(45.0, 45.0);
        final geo ToPlace = new geo(46.0, 46.0);
        long distance = cosinesCalc.between(FromPlace, ToPlace, 0);
        assertEquals(0, distance, "Distance should be zero if earth radius is zero.");
    }

    @Test
    @DisplayName("aaberer: Test negative latitude and longitude")
    public void testNegativeLatLon() {
        final geo FromPlace = new geo(-45.0, -45.0);
        final geo ToPlace = new geo(-46.0, -46.0);
        long distance = cosinesCalc.between(FromPlace, ToPlace, 6371);
        assertTrue(distance > 0, "Distance should be positive for valid negative coordinates.");
    }

    @Test
    @DisplayName("aaberer: Test invalid coordinates (latitude >= 90 or =< -90)")
    public void testInvalidLatitude() {
        final geo FromPlace = new geo(100.0, 0.0); // invalid latitude
        final geo ToPlace = new geo(-100.0, 0.0); // invalid latitude
        long distance = cosinesCalc.between(FromPlace, ToPlace, 6371);
        assertFalse(distance < 0, "Distance for invalid coordinates.");
    }

    @Test
    @DisplayName("aknott: Test with very large Earth radius value")
    public void testLargeEarthRadius() {
        final geo FromPlace = new geo(10.0, 10.0);
        final geo ToPlace = new geo(20.0, 20.0);
        long distance = cosinesCalc.between(FromPlace, ToPlace, 12742000); 
        assertEquals(3089515, distance); 
    }

    @Test
    @DisplayName("aknott: Test with equator and maximum longitude edge case")
    public void testEquatorEdgeCase() {
        final geo FromPlace = new geo(0.0, 179.9999); 
        final geo ToPlace = new geo(0.0, -179.9999); 
        long distance = cosinesCalc.between(FromPlace, ToPlace, 6371000); 
        assertEquals(22,distance);
    }
    @Test
    @DisplayName("nlrohr: Test maximum distance between antipodal points")
    public void testAntipodalPoints() {
        final geo FromPlace = new geo(0.0, 0.0);        // A point on the equator
        final geo ToPlace = new geo(-0.0, 180.0);       // Directly opposite point
        long distance = cosinesCalc.between(FromPlace, ToPlace, 6371000); // Earth's radius in meters
        long expectedDistance = 20015086; // Earth's circumference / 2 in meters
        assertEquals(expectedDistance, distance, 100, "Distance should match Earth's half-circumference.");
    }
}
