public class DMSCoordinate {
    int degreesLat;
    int minutesLat;
    int secondsLat;

    int degreesLong;
    int minutesLong;
    int secondsLong;

    public DMSCoordinate() {
        degreesLat = 0;
        minutesLat = 0;
        secondsLat = 0;

        degreesLong = 0;
        minutesLong = 0;
        secondsLong = 0;
    }

    public DMSCoordinate(String latAndLong) {
        String latitude = latAndLong.substring(0, latAndLong.indexOf(" "));
        String longitude = latAndLong.substring(latAndLong.indexOf(" ")+  1);

        setLatitude(latitude);
        setLongitude(longitude);
    }

    public DMSCoordinate(int degreesLat, int minutesLat, int secondsLat, int degreesLong, int minutesLong, int secondsLong) {
        this.degreesLat = degreesLat;
        this.minutesLat = minutesLat;
        this.secondsLat = secondsLat;

        this.degreesLong = degreesLong;
        this.minutesLong = minutesLong;
        this.secondsLong = secondsLong;
    }

    public int getDegreesLat() {
        return degreesLat;
    }

    public int getMinutesLat() {
        return minutesLat;
    }

    public int getSecondsLat() {
        return secondsLat;
    }

    public int getDegreesLong() {
        return degreesLong;
    }

    public int getMinutesLong() {
        return minutesLong;
    }

    public int getSecondsLong() {
        return secondsLong;
    }

    public String getLatitude() {
        String str = degreesLat + "°" + minutesLat + "'" + secondsLat + "\"";
        return str;
    }

    public String getLongitude() {
        String str = degreesLong + "°" + minutesLong + "'" + secondsLong + "\"";
        return str;
    }

    public void setLatitude(String latitude) {
        String strDegrees = latitude.substring(0, latitude.indexOf("°"));
        String strMinutes = latitude.substring(latitude.indexOf("°") + 1 , latitude.indexOf("'"));
        String strSeconds = latitude.substring(latitude.indexOf("'") + 1, latitude.indexOf("\""));

        int parsedDegrees = Integer.parseInt(strDegrees);
        int parsedMinutes = Integer.parseInt(strMinutes);
        int parsedSeconds = Integer.parseInt(strSeconds);

        if (parsedDegrees > 60 || parsedDegrees < 0 || parsedMinutes > 60 || parsedMinutes < 0 || parsedSeconds > 60 || parsedSeconds < 0){
            parsedDegrees = 0 ;
            parsedMinutes = 0 ;
            parsedSeconds = 0;
        }
        this.degreesLat = parsedDegrees;
        this.minutesLat = parsedMinutes;
        this.secondsLat = parsedSeconds;
    }

    public void setLongitude(String longitude) {
        String strDegrees = longitude.substring(0, longitude.indexOf("°"));
        String strMinutes = longitude.substring(longitude.indexOf("°") + 1, longitude.indexOf("'"));
        String strSeconds = longitude.substring(longitude.indexOf("'") + 1, longitude.indexOf("\""));

        int parsedDegrees = Integer.parseInt(strDegrees);
        int parsedMinutes = Integer.parseInt(strMinutes);
        int parsedSeconds = Integer.parseInt(strSeconds);

        if (parsedDegrees > 60 || parsedDegrees < 0 || parsedMinutes > 60 || parsedMinutes < 0 || parsedSeconds > 60 || parsedSeconds < 0){
            parsedDegrees = 0;
            parsedMinutes = 0;
            parsedSeconds = 0;
        }
        this.degreesLong=parsedDegrees;
        this.minutesLong=parsedMinutes;
        this.secondsLong=parsedSeconds;
    }

    public String toString() {
        String str = getLatitude() + " " + getLongitude();
        return str;
    }
}
