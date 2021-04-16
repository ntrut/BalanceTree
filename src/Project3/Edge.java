package Project3;

import java.nio.file.Path;

public class Edge
{
    private PathNode destination;
    private PathNode parent;

    /*cosine similarity*/
    private double weight;

    public Edge(PathNode destination, PathNode parent, double weight)
    {
        this.destination = destination;
        this.weight = weight;
        this.parent = parent;
    }

    public PathNode getDestination() {
        return destination;
    }

    public void setDestination(PathNode destination) {
        this.destination = destination;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public PathNode getParent() {
        return parent;
    }

    public void setParent(PathNode parent) {
        this.parent = parent;
    }
}
