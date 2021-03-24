package Application;

import java.util.ArrayList;

public class KMedoidClustering
{
    private ArrayList<Cluster> allClusters = new ArrayList<Cluster>();
    private int MAX_CLUSTER;

    public KMedoidClustering(int k) {
        this.MAX_CLUSTER = k;
    }

    public ArrayList<Cluster> getAllClusters() {
        return allClusters;
    }

    public void setAllClusters(ArrayList<Cluster> allClusters) {
        this.allClusters = allClusters;
    }

    public void assignMedoid(double cosine)
    {
        Cluster cluster = new Cluster();
        cluster.setMedoid(cosine);
        if(allClusters.size() < MAX_CLUSTER)
        {
            allClusters.add(cluster);
        }

    }

    public void assignNonMedoids(ArrayList<Double> nonmedoids)
    {

        for(int i = 0; i < nonmedoids.size(); i++)
        {
            Cluster closest = allClusters.get(0);
            double closestDistance = calculateDistance(nonmedoids.get(i), allClusters.get(0).getMedoid());
            for(int j = 1; j < allClusters.size(); j++)
            {
                /*find the closest medoid*/
                double compare = calculateDistance(nonmedoids.get(i), allClusters.get(j).getMedoid());
                if(compare < closestDistance)
                {
                    closestDistance = compare;
                    closest = allClusters.get(j);
                }
            }
            closest.addNonMedoid(nonmedoids.get(i));
        }
    }

    public double calculateDistance(double input, double mediod)
    {
        return Math.abs(mediod - input);
    }
}
