package cs250.ec.order;

public class Equation {
    public static void main(String[] args){
        if (args.length != 2){
            System.out.println("java cs250.ec.order.Equation <x> <y>");
            System.exit(-1);
        }
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        System.out.println("f(x,y) = " + equationCalculation(x,y));
    }

    private static double equationCalculation(int x, int y){
        double dbX = (double) x;
        double dbY = (double) y;
        double result = equationNumerator(dbX, dbY) / equationDenominator(dbX, dbY);
        return Math.round(result * 100000) / 100000.0;
    }

    private static double equationNumerator(double x, double y){
        double logInside = Math.abs(x * y);
        double log = Math.log10(logInside);
        double exponent = Math.pow(x, 4);
        return log - exponent;
    }

    private static double equationDenominator(double x, double y){
        double rootInside = Math.pow(x * y, 2);
        double root = Math.sqrt(rootInside);
        double exponent = Math.pow(y, 3);
        double expTimes = exponent * x;
        return root + expTimes;
    }
}
