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
        int num = 5;
        ArrayList<Double> test = new ArrayList<Double>();
        KMedoidClustering clust = new KMedoidClustering(num);
        test.add(0.2);
        test.add(0.4);
        test.add(0.6);
        test.add(0.8);
        test.add(1.0);
        test.add(0.42);
        test.add(0.3434);
        test.add(0.932432);
        test.add(0.3232546);
        test.add(0.0);
        test.add(0.1);
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

    }
}
