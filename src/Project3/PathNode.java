package Project3;

import java.util.ArrayList;

public class PathNode
{
    private ArrayList<Edge> neighbors;
    private String business;
    private double distance;
    private double similarity;


    public PathNode(String business, double distance, double similarity)
    {
        this.business = business;
        this.distance = distance;
        this.similarity = similarity;
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

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }
}
