import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.CsvSource;
import java.io.*;
import java.util.ArrayList;

public class TestP1 {

  P1 p1Test = new P1();
  MathMean mathMean = new MathMean();
  MathStandardDeviation mathStandardDeviation = new MathStandardDeviation();

  @Test
  public void checkIfMainMethodCompiles() {
    P1.main(null);
  }

  @Test
  public void checkIfDivideByZero() { // UNDEFINED
    ArrayList<Double> doubleArr = new ArrayList<>();
    assertEquals(Double.NaN, MathMean.computeMean(doubleArr));
  }

  @Test
  public void checkMathAverage() {
    ArrayList<Double> doubleArr = new ArrayList<>();
    doubleArr.add(2.0);
    doubleArr.add(4.0);
    doubleArr.add(5.0);
    doubleArr.add(6.0);
    doubleArr.add(8.0);
    double result = MathMean.computeMean(doubleArr);
    assertEquals(5.0, result, 0.001); // expected,actual,delta(max accepted error)
  }

  @Test
  public void checkMathStandardDeviation() {
    ArrayList<Double> doubleArr = new ArrayList<>();
    doubleArr.add(2.0);
    doubleArr.add(4.0);
    doubleArr.add(5.0);
    doubleArr.add(6.0);
    doubleArr.add(8.0);
    double result = MathStandardDeviation.computeStandardDeviation(doubleArr);
    assertEquals(2.236, result, 0.001);
  }
}
