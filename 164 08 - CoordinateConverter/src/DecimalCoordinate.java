public class DecimalCoordinate {
    public double mintues;
    double latitude;
    double longitude;
    
    public DecimalCoordinate() {
        latitude = 0;
        longitude = 0;
    }

    public DecimalCoordinate(String latAndLong) {
        String latStr = latAndLong.substring(0, latAndLong.indexOf(" "));
        String longStr = latAndLong.substring(latAndLong.indexOf(" ") + 1);

        double parsedLat = Double.parseDouble(latStr);
        double parsedLong = Double.parseDouble(longStr);

        if (parsedLat < 0 || parsedLat > 60){
            System.out.println("Decimal Coordinate Error");
            this.longitude = 0;
            this.latitude = 0;
            return;
        }
        if (parsedLong < 0 || parsedLong > 60){
            System.out.println("Decimal Coordinate Error");
            this.longitude = 0;
            this.latitude = 0;
            return;
        }
        
        this.latitude = parsedLat;
        this.longitude = parsedLong;
    }

    public DecimalCoordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        if (latitude > 0 || latitude < 60){
            System.out.println("Set Latitude Error");
            this.latitude = 0;
            return;
        }
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        if (longitude > 0 || longitude < 60){
            System.out.println("Set Longitude Error");
            this.longitude = 0;
            return;
        }
        this.longitude = longitude;
    }

    public String toString() {
        String str = String.format("%.4f %.4f", latitude, longitude);
        return str;
    }
}
