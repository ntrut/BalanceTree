package Application;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.Random;

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
        Cluster cluster = new Cluster(cosine);
        //cluster.setMedoid(cosine);
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
                if(compare < closestDistance )
                {
                    closestDistance = compare;
                    closest = allClusters.get(j);
                }
            }
            closest.addNonMedoid(nonmedoids.get(i));
        }

        /*calculate total sum of all clsuters*/
        calculateAllSums(allClusters);
    }

    public ArrayList<Cluster> calculateAllSums(ArrayList<Cluster> clusters)
    {
        for(int i =0; i < clusters.size(); i++)
        {
            clusters.get(i).setTotal_sum_distance(0);
        }

        for(int i =0; i < clusters.size(); i++)
        {
            clusters.get(i).calculateTotalDistance();
        }
        return clusters;
    }

    public double calculateDistance(double input, double mediod)
    {
        return Math.abs(mediod - input);
    }

    public void Repeat(ArrayList<Double> nonmedoids)
    {
        Random rand = new Random();
        Random rand2 = new Random();
        Swapping swap = new Swapping();

        for(int i = 0; i < 50000; i++)
        {
            int selectClust = rand.nextInt(allClusters.size());
            if(allClusters.get(selectClust).getNonmedoids().size() != 0)
            {
                int selectNonMedoid = rand2.nextInt(allClusters.get(selectClust).getNonmedoids().size());

                /*get the cluster and remove the nonmedoid from the current nonmedoid array because we are swapping it*/
                Cluster cluster = allClusters.get(selectClust);
                double nonmedoid = cluster.getNonmedoids().remove(selectNonMedoid);
                double tempMedoid = allClusters.get(selectClust).getMedoid();

                /*swap medoid and nonmedoid*/
                cluster.setMedoid(nonmedoid);
                cluster.addNonMedoid(tempMedoid);

                ArrayList<Cluster> temp = new ArrayList<>(allClusters);
                temp.set(selectClust, cluster);


                /*check if that cluster has a smaller total sum of distance*/
                ArrayList<Cluster> hello = new ArrayList<>();
                for(int j =0; j < MAX_CLUSTER; j++)
                {
                    hello.add(new Cluster(allClusters.get(j)));
                }

                allClusters = swap.swapCheck(selectClust, temp, hello);
                calculateAllSums(allClusters);
            }

        }
    }

}
