package com.tco.misc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

public class TestOptimizerFactory {

    @Test
    @DisplayName(" nlrohr:No optimization when response time <= 50 ms")
    public void testGet_NoOptimizationForLowResponse() {
        OptimizerFactory factory = new OptimizerFactory();
        TourConstruction optimizer = factory.get(1, 45.0); // Response time less than or equal to 50 ms
        assertTrue(optimizer instanceof NoOptimizer, "Expected NoOptimizer for response time <= 50");
    }
    @Test
    @DisplayName(" nlrohr:No opt test when N=0")
    public void testGet_NoOptimizationForNEquals0() {
        OptimizerFactory factory = new OptimizerFactory();
        TourConstruction optimizer = factory.get(0, 100.0); // N = 0, response > 50 ms
        assertTrue(optimizer instanceof OneOptimizer, "Expected OneOptimizer when N equals 0");
    }
    @Test
    @DisplayName(" nlrohr:No opt test when N=1")
    public void testGet_OneOptimizerForNEquals1() {
        OptimizerFactory factory = new OptimizerFactory();
        TourConstruction optimizer = factory.get(2000, 100.0); // N = 1, response > 50 ms
        assertTrue(optimizer instanceof NoOptimizer, "Expected NoOptimizer when N equals 1");
    }
    @Test
    @DisplayName(" nlrohr:No opt test for large N values")
    public void testGet_OneOptimizer() {
        OptimizerFactory factory = new OptimizerFactory();
        TourConstruction optimizer = factory.get(2000, 100.0); // Unknown N, response > 50 ms
        assertTrue(optimizer instanceof NoOptimizer, "Expected NoOptimizer large places ");
    }

}
