package cs250.hw1;
import java.util.Arrays;
import java.lang.Math;

public class Operations {
    // TASK 2
    public static String task2TypeCheck(String str){
        if (str.startsWith("0b")){ // Binary Catch
            return "Binary";
        } else if (str.startsWith("0x")){ // Hex Catch
            return "Hexadecimal";
        } else { // Default Decimal
            return "Decimal";
        }
    }
    // END TASK 2
    // TASK 3
    public static boolean task3ErrorCheck(String str){
        if (str.startsWith("0b")){ // Binary
            for (char c : str.substring(2).toCharArray()){
                if (c != '0' && c != '1') {return false;}
            }
        } else if (str.startsWith("0x")){ // Hex
            for (char c : str.substring(2).toCharArray()){
                if (!Character.isDigit(c) && !(c >= 'a' && c <= 'f') && !(c >= 'A' && c <= 'F')){return false;}
            }
        } else { // Deci
            for (char c : str.toCharArray()) {
                if (!Character.isDigit(c)) {return false;}
            }
        }
        return true;
    }
    // END TASK 3
    // TASK 4
    public static String task4TypeConversion(String str){
        String binaryString;
        String decimalString;
        String hexString;
        String returnString = ""; // START, BIN, DEC, HEX
        // methods deci -> binary, binary -> hex, hex -> deci

        if (task2TypeCheck(str).equals("Binary")){
            binaryString = str.substring(2);
            decimalString = task4BinaryToDecimal(binaryString); // to deci | bin -> hex | hex -> deci
            hexString = task4BinaryToHex(binaryString); // to hex
            returnString = "Start=" + str + ",Binary=0b" + binaryString + ",Decimal=" + decimalString + ",Hexadecimal=" + hexString;
            return returnString;
        }
        if (task2TypeCheck(str).equals("Decimal")){
            decimalString = str;
            binaryString = task4DecimalToBinary(decimalString); // to binary
            hexString = task4BinaryToHex(binaryString); // to hex | deci -> bin | bin -> hex
            returnString = "Start=" + str + ",Binary=0b" + binaryString + ",Decimal=" + decimalString + ",Hexadecimal=" + hexString;
            return returnString;
        }
        if (task2TypeCheck(str).equals("Hexadecimal")){
            hexString = str.substring(2);
            decimalString = task4HexToDecimal(hexString); // to deci
            binaryString = task4DecimalToBinary(decimalString); // to binary | hex -> deci | deci -> bin
            returnString = "Start=" + str + ",Binary=0b" + binaryString + ",Decimal=" + decimalString + ",Hexadecimal=0x" + hexString;
            return returnString;
        }
        return returnString;
    }
    public static String task4BinaryToHex(String str){
        String hexString = "";
        int decimal = 0;
        int power = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            int digit = c - '0';
            decimal += digit * Math.pow(2, power);
            power++;
        }
        while (decimal != 0) {
            int remainder = decimal % 16;
            char hexDigit;
            if (remainder < 10) {
                hexDigit = (char)(remainder + '0');
            } else {
                hexDigit = (char)(remainder - 10 + 'a');
            }
            hexString = hexDigit + hexString;
            decimal /= 16;
        }
        return "0x" + hexString;
    }
    public static String task4BinaryToDecimal(String str){
        int decimal = 0;
        int power = 0;
        for (int i = str.length() - 1; i >= 0; --i){
            char c = str.charAt(i);
            int digit = c - '0';
            decimal += digit * Math.pow(2, power);
            ++power;
        }
        return String.valueOf(decimal);
    }
    public static String task4DecimalToBinary(String str){
        int decimal = 0;
        String binary = "";
        for (char c : str.toCharArray()){
            decimal = decimal * 10 + (c - 48); // ascii
        }
        while (decimal > 0) {
            binary = (decimal % 2) + binary;
            decimal /= 2;
        }
        if (binary.equals("")){
            return "0b0";
        } else {
            return binary;
        }
    }
    public static String task4HexToDecimal(String str){
        int decimal = 0;
        int power = 0;
        int convertInt;
        char currentPointer;
        for (int i = str.length() - 1; i >= 0; --i){
            currentPointer = str.charAt(i);
            if (Character.isDigit(currentPointer)){
                convertInt = currentPointer - '0';
            } else {
                convertInt = Character.toUpperCase(currentPointer) - 'A' + 10;
            }
            decimal += convertInt * Math.pow(16, power);
            ++power;
        }
        return String.valueOf(decimal);
    }
    // END TASK 4
    // TASK 5
    public static String task5ComputeOnesComplement(String str){
        String binaryString = "";
        String originalBinaryString = "";
        if (task2TypeCheck(str).equals("Binary")){
            binaryString = str.substring(2);
            originalBinaryString = binaryString;
        }
        if (task2TypeCheck(str).equals("Decimal")){
            binaryString = task4DecimalToBinary(str);
            originalBinaryString = binaryString;
        }
        if (task2TypeCheck(str).equals("Hexadecimal")){
            binaryString = task4DecimalToBinary(task4HexToDecimal(str.substring(2)));
            originalBinaryString = binaryString;
        }
        binaryString = binaryString.replace('0', '2');
        binaryString = binaryString.replace('1', '0');
        binaryString = binaryString.replace('2', '1');
        return str + "=" + originalBinaryString + "=>" + binaryString;
    }
    // END TASK 5
    // TASK 6
    public static String task6ComputeTwosComplement(String str){
        String binaryString = "";
        String originalBinaryString = "";
        if (task2TypeCheck(str).equals("Binary")){
            binaryString = str.substring(2);
            originalBinaryString = binaryString;
        }
        if (task2TypeCheck(str).equals("Decimal")){
            binaryString = task4DecimalToBinary(str);
            originalBinaryString = binaryString;
        }
        if (task2TypeCheck(str).equals("Hexadecimal")){
            binaryString = task4DecimalToBinary(task4HexToDecimal(str.substring(2)));
            originalBinaryString = binaryString;
        }
        binaryString = task5ComputeOnesComplement(str);
        char[] binaryArray = binaryString.toCharArray();
        for (int i = binaryArray.length - 1; i >= 0; --i){
            if (binaryArray[i] == '0'){
                binaryArray[i] = '1';
                break;
            } else {binaryArray[i] = '0';}
        }
        binaryString = String.valueOf(binaryArray);
        return str + "=" + originalBinaryString + "=>" + binaryString;
    }
    // END TASK 6
    // TASK 7
    public static String task7BitwiseOR(String binaryString1, String binaryString2, String binaryString3){
        String returnString = "";
        for (int i = 0; i < binaryString1.length(); ++i){
            char bit1 = binaryString1.charAt(i);
            char bit2 = binaryString2.charAt(i);
            char bit3 = binaryString3.charAt(i);
            char result;
            if (bit1 == '1' || bit2 == '1' || bit3 == '1'){
                result = '1';
            } else {
                result = '0';
            }
            returnString += result;
        }
        return binaryString1 + "|" + binaryString2 + "|" + binaryString3 + "=" + returnString;
    }

    public static String task7BitwiseAND(String binaryString1, String binaryString2, String binaryString3){
        String returnString = "";
        for (int i = 0; i < binaryString1.length(); ++i){
            char bit1 = binaryString1.charAt(i);
            char bit2 = binaryString2.charAt(i);
            char bit3 = binaryString3.charAt(i);
            char result;
            if (bit1 == '1' && bit2 == '1' && bit3 == '1'){
                result = '1';
            } else {
                result = '0';
            }
            returnString += result;
        }
        return binaryString1 + "&" + binaryString2 + "&" + binaryString3 + "=" + returnString;
    }
    public static String task7BitwiseXOR(String binaryString1, String binaryString2, String binaryString3){
        String returnString = "";
        for (int i = 0; i < binaryString1.length(); ++i){
            char bit1 = binaryString1.charAt(i);
            char bit2 = binaryString2.charAt(i);
            char bit3 = binaryString3.charAt(i);
            char result;
            if (bit1 == bit2 && bit2 == bit3){
                result = '0';
            } else {
                result = '1';
            }
            returnString += result;
        }
        return binaryString1 + "^" + binaryString2 + "^" + binaryString3 + "=" + returnString;
    }
    // END TASK 7
    // TASK 8
    public static String task8BinaryLeftShift(String str){
        return str + "00";
    }
    public static String task8BinaryRightShift(String str){
        return str.substring(0, str.length() - 2);
    }
    // END TASK 8

    public static void main(String args[]){
        // 1 decimal, 1 binary, 1 hex
        if (args.length != 3){
            System.out.println("Incorrect number fof arguments have been provided. Program Terminating!");
            System.exit(0);
        }
        System.out.println("Task 1");
        System.out.println("Correct number of arguments given.");
        String arg1String = args[0]; 
        String arg2String = args[1];
        String arg3String = args[2];
        // String arg1String = "15"; 
        // String arg2String = "0b1011";
        // String arg3String = "0xfa";

        System.out.println("Task 2");
        System.out.println(arg1String + "=" + task2TypeCheck(arg1String));
        System.out.println(arg2String + "=" + task2TypeCheck(arg2String));
        System.out.println(arg3String + "=" + task2TypeCheck(arg3String));

        System.out.println("Task 3");
        System.out.println(arg1String + "=" + task3ErrorCheck(arg1String));
        System.out.println(arg2String + "=" + task3ErrorCheck(arg2String));
        System.out.println(arg3String + "=" + task3ErrorCheck(arg3String));
        
        System.out.println("Task 4");
        System.out.println(task4TypeConversion(arg1String));
        System.out.println(task4TypeConversion(arg2String));
        System.out.println(task4TypeConversion(arg3String));

        System.out.println("Task 5");
        System.out.println(task5ComputeOnesComplement(arg1String));
        System.out.println(task5ComputeOnesComplement(arg2String));
        System.out.println(task5ComputeOnesComplement(arg3String));

        System.out.println("Task 6");
        System.out.println(task6ComputeTwosComplement(arg1String));
        System.out.println(task6ComputeTwosComplement(arg2String));
        System.out.println(task6ComputeTwosComplement(arg3String));

        System.out.println("Task 7");
        String binaryString1 = "";
        String binaryString2 = "";
        String binaryString3 = "";
        if (task2TypeCheck(arg1String).equals("Binary")){ // ARG 1 To Bin
            binaryString1 = arg1String.substring(2);
        }
        if (task2TypeCheck(arg1String).equals("Decimal")){
            binaryString1 = task4DecimalToBinary(arg1String);
        }
        if (task2TypeCheck(arg1String).equals("Hexadecimal")){
            binaryString1 = task4DecimalToBinary(task4HexToDecimal(arg1String.substring(2)));
        }
        if (task2TypeCheck(arg2String).equals("Binary")){ // ARG 2 To Bin
            binaryString2 = arg2String.substring(2);
        }
        if (task2TypeCheck(arg2String).equals("Decimal")){
            binaryString2 = task4DecimalToBinary(arg2String);
        }
        if (task2TypeCheck(arg2String).equals("Hexadecimal")){
            binaryString2 = task4DecimalToBinary(task4HexToDecimal(arg2String.substring(2)));
        }
        if (task2TypeCheck(arg3String).equals("Binary")){ // ARG 3 To Bin
            binaryString3 = arg3String.substring(2);
        }
        if (task2TypeCheck(arg3String).equals("Decimal")){
            binaryString3 = task4DecimalToBinary(arg3String);
        }
        if (task2TypeCheck(arg3String).equals("Hexadecimal")){
            binaryString3 = task4DecimalToBinary(task4HexToDecimal(arg3String.substring(2)));
        }
        System.out.println(task7BitwiseOR(binaryString1,binaryString2,binaryString3));
        System.out.println(task7BitwiseAND(binaryString1,binaryString2,binaryString3));
        System.out.println(task7BitwiseXOR(binaryString1,binaryString2,binaryString3));

        System.out.println("Task 8");
        System.out.println(binaryString1 + "<<2=" + task8BinaryLeftShift(binaryString1) + "," + binaryString1 + ">>2=" + task8BinaryRightShift(binaryString1));
        System.out.println(binaryString2 + "<<2=" + task8BinaryLeftShift(binaryString2) + "," + binaryString2 + ">>2=" + task8BinaryRightShift(binaryString2));
        System.out.println(binaryString3 + "<<2=" + task8BinaryLeftShift(binaryString3) + "," + binaryString3 + ">>2=" + task8BinaryRightShift(binaryString3));
    }
}