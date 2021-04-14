package Project3;

class Haversine
{

    public double getHaversine(double latitude1, double longitude1, double latitude2, double longitude2)
    {
        int r = 6371; //radius of the earth

        //distance between latitude and longitude
        double distanceLat = Math.toRadians(latitude2 - latitude1);
        double distanceLon = Math.toRadians(longitude2 - longitude1);

        //convert latitude to radians
        latitude1 = Math.toRadians(latitude1);
        latitude2 = Math.toRadians(latitude2);

        double a = Math.pow(Math.sin(distanceLat / 2), 2) + Math.pow(Math.sin(distanceLon / 2), 2) * Math.cos(latitude1) * Math.cos(latitude2);

        double c = 2 * Math.asin(Math.sqrt(a));

        return r * c; //returns in meters?

    }
}

