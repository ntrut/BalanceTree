package Application;

import java.util.ArrayList;

public class Cluster
{
    private double medoid;
    private ArrayList<Double> nonmedoids = new ArrayList<Double>();
    private double total_sum_distance;

    public Cluster(double medoid) {
        this.medoid = medoid;
    }

    public Cluster(Cluster cluster)
    {
        this.medoid = cluster.getMedoid();
        this.nonmedoids = cluster.getNonmedoids();
        this.total_sum_distance = cluster.getTotal_sum_distance();
    }
    public double getMedoid() {
        return medoid;
    }

    public void setMedoid(double medoid) {
        this.medoid = medoid;
    }

    public ArrayList<Double> getNonmedoids() {
        return nonmedoids;
    }

    public void setNonmedoids(ArrayList<Double> nonmedoids) {
        this.nonmedoids = nonmedoids;
    }

    public double getTotal_sum_distance() {
        return total_sum_distance;
    }

    public void setTotal_sum_distance(double total_sum_distance) {
        this.total_sum_distance = total_sum_distance;
    }

    public void calculateTotalDistance()
    {
        total_sum_distance = 0;
        for(int i =0; i < nonmedoids.size(); i++)
        {
            total_sum_distance = total_sum_distance + Math.abs(medoid - nonmedoids.get(i));
        }
    }

    public void addNonMedoid(double t)
    {
        nonmedoids.add(t);
        total_sum_distance = total_sum_distance + Math.abs(medoid - t);
    }

    public void print()
    {
        System.out.print("[Medoid: " + medoid + "] ");
        System.out.print("Total sum: " + total_sum_distance + ": ");
        for(int i = 0; i < nonmedoids.size(); i++)
        {
            System.out.print(nonmedoids.get(i) + ", ");
        }
        System.out.println();
    }
}
