import java.util.Random;

public class StonkMarket {

    private double exchangeRate = 1;
    private static final Random RANDY = new Random();

    public double fazCoinToUSD(int fazCoin){
        return (fazCoin * exchangeRate) / 100.0;
    }
    public double getExchangeRate() {
        return exchangeRate;
    }
    public double changeExchangeRate(){
        exchangeRate += (RANDY.nextDouble() - 0.3);
        return exchangeRate;
    }
}
