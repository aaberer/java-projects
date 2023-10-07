import java.util.Scanner;

public class ConverterMain {
    
    public static void go(Scanner scnr) {
        System.out.println("Welcome to the Degree Converter™!");
        System.out.println("Please type \"X\" when you are ready to quit!");

        System.out.println("Decimal Format: 38.6541 25.5409");
        System.out.println("DMS Format: 38°53'12\" 25°03'22\"");

        System.out.print("Please enter either format to be converted: ");
        String newCoord = scnr.nextLine();

        while(true) {
            //We can assume the provided String is a DMS coordinate if it includes a Degrees symbol.
            if(newCoord.contains("°")) {
                DMSCoordinate dmsCoord = new DMSCoordinate(newCoord);
                DMSConverter dmsConverter = new DMSConverter(dmsCoord);
                dmsConverter.convert();

                DecimalCoordinate decCoord = dmsConverter.getConvertedObj();
                System.out.println("Your converted coordinate: " + decCoord.toString());
            }
            //We can assume if it doesn't contain a degree symbol and contains a space it is a decimal coordinate.
            else if(newCoord.contains(" ")){
                DecimalCoordinate decCoord = new DecimalCoordinate(newCoord);
                DecimalConverter decConverter = new DecimalConverter(decCoord);
                decConverter.convert();

                DMSCoordinate dmsCoord = decConverter.getConvertedObj();
                System.out.println("Your converted coordinate: " + dmsCoord.toString());
            }
            //Exit case.
            else if(newCoord.equalsIgnoreCase("X")) {
                break;
            }
            //Unknown case
            else {
                System.out.println("Input not recognized, please try again.");
            }
            
            System.out.print("Please enter either format to be converted: ");
            newCoord = scnr.nextLine();
        }

        System.out.println("Thank you for using the Degree Converter™!");
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        
        go(scnr);

        scnr.close();
    }
}
