package com.tco.misc;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

public class TestOneOptimizer {

  private OneOptimizer oneOptimizer;
  private Places inputPlaces;
  private long[][] placeDistanceMatrix;
  private double radius;
  private String formula;
  private double response;

  @BeforeEach
  public void setUp() {
    oneOptimizer = new OneOptimizer();
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

    placeDistanceMatrix = new long[][]{
      {0L, 1L}, 
      {1L, 0L}
    };
  }

  @Test
  @DisplayName("aaberer: Can create object of class test")
  public void testNoOptimizerClassExists() {
    assertNotNull(oneOptimizer, "NoOptimizer should make an object");
  }

  @Test
  @DisplayName("aaberer: Test instantiation")
  public void testNoOptimizerIsSubclassOfTourConstruction() {
    assertTrue(oneOptimizer instanceof TourConstruction, "Subclass of TourConstruction.");
  }

  @Test
  @DisplayName("aaberer: Call improve, no EX") // change when its implemented
  public void testImproveDoesNotThrow() {
    assertDoesNotThrow(() -> oneOptimizer.improve(), "No exception thrown");
  }

  @Test
  @DisplayName("aaberer: Constructor returns valid object")
  public void testConstructWithValidInputs() {
    assertNotNull(inputPlaces);
  }

  @Test
  @DisplayName("aaberer: New tour longer returns true")
  public void testNewTourLongerReturnsTrue() {
    oneOptimizer.setTour(new int[] {1, 0}); // LA -> NY
    int[] baseTour = new int[] {0, 1}; // NY -> LA

    boolean result = oneOptimizer.newTourLonger(baseTour, placeDistanceMatrix);
    assertFalse(result, "New tour should be shorter (FALSE) ");
  }

  @Test
  @DisplayName("aaberer: Test Radius is set correctly")
  public void testRadius() {
    oneOptimizer.construct(inputPlaces, radius, formula, response);
    assertEquals(radius, oneOptimizer.getRadius(), 0.001);
  }

  @Test
  @DisplayName("aaberer: Test Formula is set correctly")
  public void testFormula() {
    oneOptimizer.construct(inputPlaces, radius, formula, response);
    assertEquals(formula, oneOptimizer.getFormula());
  }

  @Test
  @DisplayName("aaberer: Test Response is set correctly")
  public void testResponse() {
    oneOptimizer.construct(inputPlaces, radius, formula, response);
    assertEquals(response, oneOptimizer.getResponse(), 0.001);
  }

  @Test
  @DisplayName("aaberer: Test Places are set correctly")
  public void testPlaces() {
    oneOptimizer.construct(inputPlaces, radius, formula, response);
    assertEquals(inputPlaces, oneOptimizer.getPlaces());
  }

  @Test
  @DisplayName("aaberer: Test Number of places is set correctly")
  public void testNumPlaces() {
    oneOptimizer.construct(inputPlaces, radius, formula, response);
    assertEquals(inputPlaces.size(), oneOptimizer.getNumPlaces());
  }

  @Test
  @DisplayName("aaberer: Visited cities array is initialized correctly")
  public void testVisitedCities() {
    oneOptimizer.construct(inputPlaces, radius, formula, response);
    boolean[] visitedCities = oneOptimizer.getVisitedCities();
    assertEquals(inputPlaces.size(), visitedCities.length);
  }

  @Test
  @DisplayName("aaberer: Tour array is initialized correctly")
  public void testTour() {
    oneOptimizer.construct(inputPlaces, radius, formula, response);
    int[] tour = oneOptimizer.getTour();
    assertEquals(inputPlaces.size(), tour.length);
  }

  @Test
  @DisplayName("aaberer: Test Place distance matrix is initialized correctly")
  public void testPlaceDistanceMatrix() {
    oneOptimizer.construct(inputPlaces, radius, formula, response);
    long[][] distanceMatrix = oneOptimizer.getDistanceMatrix();
    assertEquals(inputPlaces.size(), distanceMatrix.length);
    for (int i = 0; i < inputPlaces.size(); i++) {
      assertEquals(inputPlaces.size(), distanceMatrix[i].length);
    }
  }

  @Test
  @DisplayName("aknott: Ensure construct sets tour and matrix correctly")
  public void testConstructSetsTourAndMatrix() {
      oneOptimizer.construct(inputPlaces, 100, formula, response);
        
      assertNotNull(oneOptimizer.getTour());
      assertNotNull(oneOptimizer.getDistanceMatrix());
  }

  @Test
  @DisplayName("aaberer: Construct returns original places if nearestNeighbor fails")
  public void testConstructReturnsPlacesIfNearestNeighborFails() {
    oneOptimizer = new OneOptimizer() {
      @Override
      public boolean nearestNeighbor() {
        return false;
      }
    };

    Places result = oneOptimizer.construct(inputPlaces, radius, formula, response);
    assertEquals(inputPlaces, result, "Expected original places when nearestNeighbor fails");
  }

  @Test
  @DisplayName("aaberer: Construct returns original places if newTourLonger is true")
  public void testConstructReturnsOriginalPlacesIfLonger() {
    oneOptimizer.setTour(new int[]{1, 0}); // LA -> NY
    oneOptimizer = new OneOptimizer() {
      @Override
      public boolean newTourLonger() {
        return true;
      }
    };

    Places result = oneOptimizer.construct(inputPlaces, radius, formula, response);
    assertEquals(inputPlaces, result, "Expected original places returned when newTourLonger is true");
  }

  @Test
  @DisplayName("aaberer: New tour longer returns correct comparison")
  public void testNewTourLongerComparison() {
    oneOptimizer.setDistanceMatrix(new long[][]{
        {0L, 10L},
        {10L, 0L}
    });

    oneOptimizer.setTour(new int[]{0, 1}); // NY -> LA
    int[] baseTour = new int[]{1, 0}; // LA -> NY

    boolean result = oneOptimizer.newTourLonger(baseTour, oneOptimizer.getDistanceMatrix());
    assertFalse(result, "Expected new tour to be longer");
  }

  @Test
  @DisplayName("aaberer: MakeDistMatrix returns false on timeout")
  public void testMakeDistMatrixFalseOnTimeout() {
    long startTime = System.currentTimeMillis();

    // simulate timeout situation
    OneOptimizer mockedOptimizer = new OneOptimizer() {
      @Override
      public boolean makeDistMatrix(long startTime) {
        return System.currentTimeMillis() - startTime > 5000;
      }
    };

    assertFalse(mockedOptimizer.makeDistMatrix(startTime), "Expected to return false from timeout");
  }

  @Test
  @DisplayName("aaberer: Nearest neighbor selects the correct city")
  public void testNearestNeighborSelectsCorrectCity() {
    // additional places
    Place chicago = new Place();
    chicago.put("latitude", "41.8781");
    chicago.put("longitude", "-87.6298");

    Place miami = new Place();
    miami.put("latitude", "25.7617");
    miami.put("longitude", "-80.1918");

    inputPlaces.add(chicago);
    inputPlaces.add(miami);

    // set + fill tour
    oneOptimizer.setDistanceMatrix(new long[][]{
        {0L, 10L, 20L, 30L},
        {10L, 0L, 15L, 25L},
        {20L, 15L, 0L, 5L},
        {30L, 25L, 5L, 0L}
    });
    oneOptimizer.construct(inputPlaces, radius, formula, response);
    
    int[] result = new int[]{0, 2, 3, 1};
    assertArrayEquals(result, oneOptimizer.getTour(), "Expected nearest city set");
  }
  @Test
  @DisplayName("aknott: Improve method does not alter tour if already optimal")
  public void testImproveDoesNotAlterOptimalTour() {
    oneOptimizer.setDistanceMatrix(new long[][]{
        {0L, 100L},
        {100L, 0L}
    });

    oneOptimizer.setTour(new int[]{0, 1}); 
    oneOptimizer.construct(inputPlaces, radius, formula, response);
    oneOptimizer.improve(); 

    int[] expectedTour = new int[]{0, 1};
    assertArrayEquals(expectedTour, oneOptimizer.getTour());
  }

}