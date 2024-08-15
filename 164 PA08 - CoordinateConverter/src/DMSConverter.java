public class DMSConverter implements Converter{

    DMSCoordinate DMSObj;

    DecimalCoordinate convertedObj;

    public DMSConverter() {
        this.DMSObj = new DMSCoordinate();
    }

    public DMSConverter(DMSCoordinate DMSObj) {
        this.DMSObj = DMSObj;
    }

    public DMSConverter(String latAndLong) {
        DMSObj = new DMSCoordinate(latAndLong);
    }

    public DecimalCoordinate getConvertedObj() {
        return convertedObj;
    }

    public void convert() {
        int secondsLat = DMSObj.getSecondsLat();
        int minutesLat = DMSObj.getMinutesLat();
        int degreesLat = DMSObj.getDegreesLat();
        double latsec = secondsLat / 3600.0;
        double doubleminlat = minutesLat / 60.0;
        double doubledegreelat = degreesLat;
        double lat1=doubledegreelat+doubleminlat+doubledegreelat;

        int secondsLong=DMSObj.getSecondsLong();
        int minutesLong=DMSObj.getMinutesLong();
        int degreesLong=DMSObj.getDegreesLong();
        double longseconds = secondsLong / 3600.0;
        double longminutes = minutesLong / 60.0;
        double degree2 = degreesLong;
        double long1 = degree2 + longminutes + longseconds;
      
        convertedObj = new DecimalCoordinate();
    }
}