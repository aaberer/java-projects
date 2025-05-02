package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static java.lang.Math.toRadians;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGeographicCoordinate {

    public class Geo implements GeographicCoordinate {
        private Double latitude;
        private Double longitude;

        public Geo(Double latitude, Double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        public Double latRadians() {
            return toRadians(latitude);
        }

        @Override
        public Double lonRadians() {
            return toRadians(longitude);
        }
    }

    public Geo geo() {
        return new Geo(40.748817, -73.985428);
    }

    @Test
    public void testGeoCoord() {
        GeographicCoordinate geo1 = geo();
        assertEquals(toRadians(40.748817), geo1.latRadians());
        assertEquals(toRadians(-73.985428), geo1.lonRadians());
    }
}

