package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static java.lang.Math.toRadians;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestVincentyCalculator {
    VincentyCalculator vincentyCalc;

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

    @BeforeEach
    public void setup(){
        vincentyCalc = new VincentyCalculator();
    }
    @Test
    @DisplayName("wwardlow: Test Opposite Longitudes")
    public void testCreator() {
        final geo FromPlace = new geo(0.0, 90.0);
        final geo ToPlace = new geo(0.0, -90.0);
        long distance = vincentyCalc.between(FromPlace, ToPlace, 6310);
        assertEquals(19823, distance);
    }

    @Test
    @DisplayName("wwardlow: Test vincenty basic distance")
    public void basicDistance() {
        final geo FromPlace = new geo(45.504061, -73.573189);
        final geo ToPlace = new geo(-51.624784, -69.213440);
        long distance = vincentyCalc.between(FromPlace, ToPlace, 6371);
        assertEquals(10808, distance);
    }

    @Test
    @DisplayName("aknott:Test distance between two identical points")
    public void testSamePoint() {
        final geo FromPlace = new geo(45.504061, -73.573189);
        final geo ToPlace = new geo(45.504061, -73.573189);
        long distance = vincentyCalc.between(FromPlace, ToPlace, 6371); 
        assertEquals(0, distance);
    }

    @Test
    @DisplayName("aknott:Test distance between Equator Points")
    public void testEquatorPoints() {
        final geo FromPlace = new geo(0.0, 0.0);
        final geo ToPlace = new geo(0.0, 90.0);
        long distance = vincentyCalc.between(FromPlace, ToPlace, 6371000); 
        assertEquals(10007543, distance);
    }

    @Test
    @DisplayName("aknott:Test distance between North Pole and Equator")
    public void testPoleToEquator() {
        final geo FromPlace = new geo(90.0, 0.0);
        final geo ToPlace = new geo(0.0, 0.0);
        long distance = vincentyCalc.between(FromPlace, ToPlace, 6371000); 
        assertEquals(10007543, distance);
    }
    @Test
    @DisplayName("wwardlow: test basic distance")
    public void testBasicDistance() {
        final geo FromPlace = new geo(10.0, 10.0);
        final geo ToPlace = new geo(20.0, 20.0);
        long distance = vincentyCalc.between(FromPlace, ToPlace, 6310);
        assertEquals(1530, distance);
    }

    @Test
    @DisplayName("nlrohr:Test very short distance between two close points")
    public void testVeryClosePoints() {
        final geo FromPlace = new geo(45.504061, -73.573189);
        final geo ToPlace = new geo(45.504063, -73.573190);
        long distance = vincentyCalc.between(FromPlace, ToPlace, 6371000);
        assertTrue(distance < 1);
    }

    @Test
    @DisplayName("nlrohr:Test distance between antipodal points")
    public void testAntipodalPoints() {
        final geo FromPlace = new geo(0.0, 0.0);
        final geo ToPlace = new geo(-0.0, 180.0);
        long distance = vincentyCalc.between(FromPlace, ToPlace, 6371000);
        long expectedDistance = 20015087;
        assertTrue(Math.abs(expectedDistance - distance) == 0); 
    }

    @Test
    @DisplayName("nlrohr: Test distance at maximum latitude (North Pole to South Pole)")
    public void testMaxMinLatitude() {
        final geo FromPlace = new geo(90.0, 0.0); // North Pole
        final geo ToPlace = new geo(-90.0, 0.0); // South Pole
        long distance = vincentyCalc.between(FromPlace, ToPlace, 6371000); 
        long expectedDistance = 20015087;
        assertTrue(Math.abs(expectedDistance - distance) == 0);
    }

    @Test
    @DisplayName("aknott: Test with very large Earth radius value")
    public void testLargeEarthRadius() {
        final geo FromPlace = new geo(10.0, 10.0);
        final geo ToPlace = new geo(20.0, 20.0);
        long distance = vincentyCalc.between(FromPlace, ToPlace, 12742000); 
        assertEquals(3089515, distance); 
    }

    @Test
    @DisplayName("aknott: Test with equator and maximum longitude edge case")
    public void testEquatorEdgeCase() {
        final geo FromPlace = new geo(0.0, 179.9999); 
        final geo ToPlace = new geo(0.0, -179.9999); 
        long distance = vincentyCalc.between(FromPlace, ToPlace, 6371000); 
        assertEquals(22,distance);
    }

    @Test
    @DisplayName("nlrohr: Test points on the Prime Meridian")
    public void testPrimeMeridian() {
        final geo FromPlace = new geo(10.0, 0.0);
        final geo ToPlace = new geo(-10.0, 0.0);
        long distance = vincentyCalc.between(FromPlace, ToPlace, 6371000);
        assertEquals(2223899, distance); // Approximate distance for 20 degrees on the Prime Meridian
    }

    @Test
    @DisplayName("nlrohr: Test with maximum valid latitude and longitude")
    public void testMaxLatitudeLongitude() {
        final geo FromPlace = new geo(90.0, 180.0);
        final geo ToPlace = new geo(-90.0, -180.0);
        long distance = vincentyCalc.between(FromPlace, ToPlace, 6371000);
        long expectedDistance = 20015087; // Distance from North Pole to South Pole
        assertEquals(expectedDistance, distance);
}
}