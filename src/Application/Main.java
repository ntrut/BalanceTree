package Application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Main
{
    public static void main(String[] args) throws IOException
    {

        /*
        int num = 10;
        ArrayList<Double> test = new ArrayList<Double>();
        KMedoidClustering clust = new KMedoidClustering(num);

        for(int k =0; k < 5000; k++)
        {
            Random rand = new Random();
            test.add((Math.random() * 1));
        }

        createClusters create = new createClusters();
        ArrayList<Cluster> clusters = create.create(num, test);

        System.out.println("Medoids: ");
        for(int i = 0; i < clusters.size(); i++)
        {
            System.out.println(clusters.get(i).getMedoid());
        }

         */

        Similiarities similiarities = new Similiarities();
        Hashtable<String, Double> test = similiarities.getSimiliartiy("Caldense & Bakery", 10, 0);
        System.out.println(test.toString());

    }
}
