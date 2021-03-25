package Application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        //Similiarities test = new Similiarities();
        //test.getSimiliartiy("Visionworks");
        int num = 10;
        ArrayList<Double> test = new ArrayList<Double>();
        KMedoidClustering clust = new KMedoidClustering(num);

        for(int k =0; k < 5000; k++)
        {
            Random rand = new Random();
            test.add((Math.random() * 1));
        }

        for(int i = 0; i < num; i++)
        {
            Random rand = new Random();
            int k = rand.nextInt(test.size());
            clust.assignMedoid(test.remove(k));
        }

        clust.assignNonMedoids(test);

        for(int i =0; i < clust.getAllClusters().size(); i++)
        {
            clust.getAllClusters().get(i).print();
        }

        System.out.println("******************************************************");
        clust.Repeat(test);
        for(int i =0; i < clust.getAllClusters().size(); i++)
        {
            System.out.print("[Cluster " + i + "] " + " ");
            clust.getAllClusters().get(i).print();
        }

    }
}
