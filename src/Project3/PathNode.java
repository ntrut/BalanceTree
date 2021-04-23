package Project3;

import java.io.Serializable;
import java.util.ArrayList;

public class PathNode implements Comparable<PathNode>, Serializable
{
    private ArrayList<Edge> neighbors = new ArrayList<Edge>();
    private PathNode parent;
    private String business;
    private double latitude;
    private double longitude;
    private double distance;


    public PathNode(String business, double latitude, double longitude)
    {
        this.business = business;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void addEdge(Edge e)
    {
        neighbors.add(e);
    }

    /*-------------------Getters and Setters--------------------*/
    public ArrayList<Edge> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(ArrayList<Edge> neighbors) {
        this.neighbors = neighbors;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public PathNode getParent() {
        return parent;
    }

    public void setParent(PathNode parent) {
        this.parent = parent;
    }

    public int compareTo(PathNode o)
    {
        double compare = o.getDistance();

        if(this.getDistance() < compare)
            return -1;
        else if(this.getDistance() > compare)
            return 1;
        else
            return 0;
    }

}
