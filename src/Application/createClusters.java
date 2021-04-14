package Application;

import java.util.ArrayList;
import java.util.Random;

public class createClusters
{
    public ArrayList<Cluster> create(int k, ArrayList<Double> nonmedoids)
    {
        KMedoidClustering clust = new KMedoidClustering(k);

        /*assigns one medoid and removes that number from the arraylist of nonmedoids*/
        for(int i = 0; i < k; i++)
        {
            Random rand = new Random();
            int randomMedoid = rand.nextInt(nonmedoids.size());
            clust.assignMedoid(nonmedoids.remove(randomMedoid));
        }

        /*assign nonmedoids and print out the first 3 steps of the cluster process*/
        clust.assignNonMedoids(nonmedoids);

        for(int i =0; i < clust.getAllClusters().size(); i++)
        {
            clust.getAllClusters().get(i).print();
        }

        /*do the steps 4-8 of the cluster process and repeat it 50,000 times*/
        System.out.println("******************************************************");
        clust.Repeat(nonmedoids);
        for(int i =0; i < clust.getAllClusters().size(); i++)
        {
            System.out.print("[Cluster " + i + "] " + " ");
            clust.getAllClusters().get(i).print();
        }

        return clust.getAllClusters();
        //System.out.println("Medoid " + clust.getAllClusters().get(indexOfCluster-1).getMedoid() + " Closests: " + get5(clust.getAllClusters().get(indexOfCluster - 1)));

    }

    /*gets the 5 closest nonmedoids to the medoid*/
    public ArrayList<Double> get5(Cluster cluster)
    {
        ArrayList<Double> finalArray = new ArrayList<>();
        System.out.println(cluster.getNonmedoids());
        int size = 10;
        if(cluster.getNonmedoids().size() < 5)
        {
            size = cluster.getNonmedoids().size();
        }

        while(finalArray.size() != 5 && cluster.getNonmedoids().size() != 0)
        {
            if(cluster.getNonmedoids().size() == 0)
            {
                break;
            }
            int index = 0;
            double min = calculateDistance(cluster.getNonmedoids().get(0), cluster.getMedoid());
            for(int j =1; j < cluster.getNonmedoids().size(); j++)
            {
                double compare = calculateDistance(cluster.getNonmedoids().get(j), cluster.getMedoid());
                if(compare < min)
                {
                    min = compare;
                    index = j;
                }
            }
            if(!finalArray.contains(cluster.getNonmedoids().get(index)))
                finalArray.add(cluster.getNonmedoids().remove(index));
            else
                cluster.getNonmedoids().remove(index);

        }
        return finalArray;
    }

    public double calculateDistance(double input, double mediod)
    {
        return Math.abs(mediod - input);
    }
}
