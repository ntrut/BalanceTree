package Application;

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

    public void Repeat(ArrayList<Double> nonmedoids)
    {
        Random rand = new Random();
        Random rand2 = new Random();

        for(int i = 0; i < 1; i++)
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

                ArrayList<Cluster> temp = allClusters;
                temp.set(selectClust, cluster);

                /*check if that cluster has a smaller total sum of distance*/

                swapCheck(selectClust, temp);
            }

        }
    }

    public ArrayList<Cluster> calculateAllSums(ArrayList<Cluster> clusters)
    {
        for(int i =0; i < clusters.size(); i++)
        {
            clusters.get(i).calculateTotalDistance();
        }
        return clusters;
    }

    public void swapCheck(int index, ArrayList<Cluster> clusters)
    {
        /*reassign all of the nonmedoids*/
        clusters = calculateAllSums(clusters);
        clusters = reAssignNonMedoids(clusters);
        clusters = calculateAllSums(clusters);
        if(clusters.get(index).getMedoid() < allClusters.get(index).getMedoid())
        {
           allClusters = clusters;
        }


    }

    public ArrayList<Cluster> reAssignNonMedoids(ArrayList<Cluster> clusters)
    {
        ArrayList<Double> newArray = new ArrayList<Double>();
        ArrayList<Double> empty = new ArrayList<Double>();
        for(int i = 0; i < clusters.size(); i++)
        {
            System.out.println(clusters.get(i).getNonmedoids());
            newArray.addAll(clusters.get(i).getNonmedoids());
            clusters.get(i).setNonmedoids(empty);
            clusters.get(i).calculateTotalDistance();
        }
        System.out.println("here");
        System.out.println(newArray);
        int index = 0;
        for(int i = 0; i < newArray.size(); i++)
        {
            double min = calculateDistance(newArray.get(i), clusters.get(0).getMedoid());
            for(int j = 1; j < clusters.size(); j++)
            {
                double compare = calculateDistance(newArray.get(i), clusters.get(j).getMedoid());
                if(compare < min)
                {
                    min = compare;
                    index = j;
                }

            }
            clusters.get(index).addNonMedoid(min);
        }



        return clusters;
    }

}
