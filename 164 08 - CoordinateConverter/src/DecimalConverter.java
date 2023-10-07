import java.lang.Math;

public class DecimalConverter implements Converter{

    DecimalCoordinate decimalObj;

    DMSCoordinate convertedObj;
    
    public DecimalConverter() {
        this.decimalObj = new DecimalCoordinate();
    }

    public DecimalConverter(DecimalCoordinate decimalObj) {
        this.decimalObj = decimalObj;
    }

    public DMSCoordinate getConvertedObj() {
        return convertedObj;
    }

    public void convert() {
        DecimalCoordinate decimanlObj2 = new DecimalCoordinate();
        int degreesLat = (int)Math.floor(decimalObj.getLatitude());
        double unroundLat = (decimalObj.getLatitude() - degreesLat) * 60;
        int minutesLat = (int)Math.floor(unroundLat);
        int secondsLat = (int)Math.floor((unroundLat - minutesLat) * 60);

        int degreesLong = (int)Math.floor(decimalObj.getLongitude());
        double unroundLong = (decimalObj.getLongitude() - degreesLong) * 60;
        int minutesLong = (int)Math.floor(decimalObj.mintues);
        int secondsLong = (int)Math.floor((unroundLong - minutesLong)) * 60;
        convertedObj = new DMSCoordinate(); 
    }
}
