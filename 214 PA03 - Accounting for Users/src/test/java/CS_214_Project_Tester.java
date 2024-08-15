import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.CsvSource;
// import java.lang.IllegalArgumentException;
import java.util.ArrayList;
// import java.io.*; 

public class CS_214_Project_Tester {

  ArrayList<Double> doubleArr = new ArrayList<Double>();

  @Test
  public void testProjectCompiles(){
    CS_214_Project project = new CS_214_Project();
  }

  @Test
  public void testCalcMean(){
    doubleArr.add(1.0);
    doubleArr.add(2.0);
    doubleArr.add(3.0);
    doubleArr.add(4.0);
    doubleArr.add(5.0);
    String expected = "3.0";
    String result = CS_214_Project.calc_mean(doubleArr);
    assertEquals(expected, result);
  }

  @Test
  public void testCalcSDV(){
    doubleArr.add(1.0);
    doubleArr.add(2.0);
    doubleArr.add(3.0);
    doubleArr.add(4.0);
    doubleArr.add(5.0);
    String expected = "1.5811388300841898";
    String result = CS_214_Project.calc_sdv(doubleArr);
    assertEquals(expected, result);
  }

  @Test
  public void testMeanUNDEFINED_Empty(){
    String expected = "UNDEFINED";
    String result = CS_214_Project.calc_mean(doubleArr);
    assertEquals(expected, result);
  }

  @Test
  public void testSDVUNDEFINED_Empty(){
    String expected = "UNDEFINED";
    String result = CS_214_Project.calc_sdv(doubleArr);
    assertEquals(expected, result);
  }

  @Test
  public void testMeanUNDEFINED_Zeros(){
    doubleArr.add(0.0);
    doubleArr.add(0.0);
    doubleArr.add(0.0);
    doubleArr.add(0.0);
    doubleArr.add(0.0);
    String expected = "UNDEFINED";
    String result = CS_214_Project.calc_mean(doubleArr);
    assertEquals(expected, result);
  }

  @Test
  public void testSDVUNDEFINED_Zeros(){
    doubleArr.add(0.0);
    doubleArr.add(0.0);
    doubleArr.add(0.0);
    doubleArr.add(0.0);
    doubleArr.add(0.0);
    String expected = "UNDEFINED";
    String result = CS_214_Project.calc_mean(doubleArr);
    assertEquals(expected, result);
  }
}