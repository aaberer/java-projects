package cs250.ec.convert;

public class DecimalToBinary{
    public static void main(String[] args){
        if (args.length != 1){
            System.out.println("java cs250.ec.convert.DecimalToBinary <decimal-number>");
            System.exit(-1);
        }
        String decimal = args[0];
        String binary = convertToBinary(decimal);
        System.out.println(decimal + " -> " + binary);
    }

    private static String convertToBinary(String str){
        double decimal = 0.0;
        String[] split = str.split("\\.");
        String strDecimal = split[1];
        double baseStart = 0.1;
        for (int i = 0; i < strDecimal.length(); ++i){
            decimal += (strDecimal.charAt(i) - '0') * baseStart;
            baseStart /= 10;
        }
        decimal = Math.round(decimal * 1000000) / 1000000.0;
        int maxLength = 20;
        StringBuilder sb = new StringBuilder();
        while (sb.length() < maxLength){
            decimal *= 2;
            if (decimal >= 1.0){
                sb.append('1');
                decimal -= 1.0;
            } else {
                sb.append('0');
            }
            if (decimal < 0.0000001) {
                break;
            }
        }
        return "0." + sb.toString();
    }
}