package Project3;

import Application.Similiarities;

import java.io.FileNotFoundException;
import java.util.Hashtable;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Similiarities similiarities = new Similiarities();
        Hashtable<String, Double> allCosines = similiarities.getSimiliartiy("Panda Express", 10, 0);
        //System.out.println(allCosines.toString());

        Haversine haversine = new Haversine();
        double lat1 = 41.5007;
        double lon1 = 100;
        double lat2 = 70.6892;
        double lon2 = 0;


        System.out.println(haversine.getHaversine(lat1, lon1, lat2, lon2));
        Graph graph = new Graph();
        graph.initailizeArrays();
        //graph.printAll();
        graph.initalizeNodes();
        //graph.printAllNodes();
        graph.initializeEdges();
        graph.printAllWithEdges();




        //System.out.println(similiarities.getOneCosineSimiliarity("Panda Express", "Kelsey's Bar and Grill"));

        /*----------------going to need to use all of the cosine values in my "allCosines" hashtable to create the clusters, but i only need it
        * for the finding shortest path algo---------------*/


    }
}
