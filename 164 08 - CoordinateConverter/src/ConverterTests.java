public class ConverterTests {
    //This tests in bounds DMSCoord
    public static boolean testDMSCoordConstruction1() {
        DMSCoordinate testDMS = new DMSCoordinate("39°58'12\" 25°29'22\"");
        boolean test1 = false;
        boolean test2 = false;

        if(testDMS.getDegreesLat() == 39) test1 = true;
        if(testDMS.getSecondsLong() == 22) test2 = true;
        
        return test1 && test2;
    }
    //This tests out of bounds DMSCoord
    public static boolean testDMSCoordConstruction2() {
        DMSCoordinate testDMS = new DMSCoordinate("39°53'12\" 25°61'22\"");

        boolean test1 = false;
        boolean test2 = false;

        if(testDMS.getDegreesLat() == 39) test1 = true;
        if(testDMS.getDegreesLong() == 0) test2 = true;
        
        return test1 && test2;
    }
    //This tests in bounds DecimalCoord
    public static boolean testDecimalCoordConstruction1() {
        DecimalCoordinate testDecimal = new DecimalCoordinate("38.6541 59.0009");

        boolean test1 = false;
        boolean test2 = false;

        if(testDecimal.getLatitude() == 38.6541) test1 = true;
        if(testDecimal.getLongitude() == 59.0009) test2 = true;
        
        return test1 && test2;
    }
    //This tests out of bounds DecimalCoord
    public static boolean testDecimalCoordConstruction2() {
        DecimalCoordinate testDecimal = new DecimalCoordinate("38.6541 190.0009");

        boolean test1 = false;
        boolean test2 = false;

        if(testDecimal.getLatitude() == 0) test1 = true;
        if(testDecimal.getLongitude() == 0) test2 = true;
        
        return test1 && test2;
    }

    public static boolean testDMSConverter() {
        DMSCoordinate testDMS = new DMSCoordinate("39°53'12\" 25°57'25\"");
        DMSConverter testConverter = new DMSConverter(testDMS);
        testConverter.convert();

        DecimalCoordinate testDecimal = testConverter.getConvertedObj();

        if(testDecimal.toString().equals("39.8867 25.9569")) return true;        
        else return false;
    }

    public static boolean testDecimalConverter() {
        DecimalCoordinate testDecimal = new DecimalCoordinate("38.9856 25.9231");
        DecimalConverter testConverter = new DecimalConverter(testDecimal);
        testConverter.convert();

        DMSCoordinate testDMS = testConverter.getConvertedObj();

        boolean test1 = false;
        boolean test2 = false;

        if(testDMS.getLatitude().equals("38°59'8\"")) test1 = true;   
        if(testDMS.getLongitude().equals("25°55'23\"")) test2 = true;
        return test1 && test2;
    }

    public static void main(String[] args) {
        System.out.println("Out of Bounds cases should print an error when run!");

        System.out.println("Testing DMSCoordinate construction (In Bounds): " + testDMSCoordConstruction1() + ", should return true when method is implemented.");
        System.out.println("Testing DMSCoordinate construction (Out of Bounds): " + testDMSCoordConstruction2() + ", should return true when method is implemented.");

        System.out.println();
        System.out.println("Testing DecimalCoordinate construction (In Bounds): " + testDecimalCoordConstruction1() + ", should return true when method is implemented.");
        System.out.println("Testing DecimalCoordinate construction (Out of Bounds): " + testDecimalCoordConstruction2() + ", should return true when method is implemented.");

        System.out.println();
        System.out.println("Testing DMSConverter conversion: " + testDMSConverter() + ", should return true when method is implemented.");
        System.out.println("Testing DecimalConverter conversion: " + testDecimalConverter() + ", should return true when method is implemented.");
    }
}
