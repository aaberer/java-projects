import javax.lang.model.element.ModuleElement.UsesDirective;

public class Wallet {

    private int fazCoin;
    private double USDollars;

    public Wallet() {
        fazCoin = 500;
        USDollars = 5.0;
    }

    public Wallet(int fazCoin, double USDollars) {
        this.fazCoin = fazCoin;
        this.USDollars = USDollars;
    }


    public int getFazCoin(){
        return fazCoin;
    }

    public void setFazCoin(int fazCoin){
        this.fazCoin = fazCoin;
    }

    public double getUSDollars(){
        return USDollars;
    }

    public void setUSDollars(double USDollars){
        this.USDollars = USDollars;
    }
}
