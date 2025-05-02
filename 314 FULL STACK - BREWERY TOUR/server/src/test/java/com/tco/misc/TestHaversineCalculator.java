package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static java.lang.Math.toRadians;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestHaversineCalculator {
    HaversineCalculator haversineCalc;

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
        haversineCalc = new HaversineCalculator();
    }

    @Test
    @DisplayName("colin00:Test haversine basic distance")
    public void basicDistance() {
        final geo FromPlace = new geo(45.504061,-73.573189);
        final geo ToPlace = new geo(-51.624784,-69.213440);
        long distance = haversineCalc.between(FromPlace,ToPlace,6371);
        assertEquals(10808, distance);
    }

    @Test
    @DisplayName("colin00:Test haversine negative earth radius")
    public void negativeRadius() {
        final geo FromPlace = new geo(45.504061,-73.573189);
        final geo ToPlace = new geo(-51.624784,-69.213440);
        long distance = haversineCalc.between(FromPlace,ToPlace,6371);
        assertEquals(10808, distance);
    }

    @Test
    @DisplayName("aknott:Test distance between two identical points")
    public void testSamePoint() {
        final geo FromPlace = new geo(45.504061, -73.573189);
        final geo ToPlace = new geo(45.504061, -73.573189);
        long distance = haversineCalc.between(FromPlace, ToPlace, 6371); 
        assertEquals(0, distance);
    }

    @Test
    @DisplayName("aknott:Test distance between Equator Points")
    public void testEquatorPoints() {
        final geo FromPlace = new geo(0.0, 0.0);
        final geo ToPlace = new geo(0.0, 90.0);
        long distance = haversineCalc.between(FromPlace, ToPlace, 6371000); 
        assertEquals(10007543, distance);
    }

    @Test
    @DisplayName("aknott: Test with very large Earth radius value")
    public void testLargeEarthRadius() {
        final geo FromPlace = new geo(10.0, 10.0);
        final geo ToPlace = new geo(20.0, 20.0);
        long distance = haversineCalc.between(FromPlace, ToPlace, 12742000); 
        assertEquals(3089515, distance); 
    }

    @Test
    @DisplayName("aknott: Test with equator and maximum longitude edge case")
    public void testEquatorEdgeCase() {
        final geo FromPlace = new geo(0.0, 179.9999); 
        final geo ToPlace = new geo(0.0, -179.9999); 
        long distance = haversineCalc.between(FromPlace, ToPlace, 6371000); 
        assertEquals(22,distance);
    }
}
