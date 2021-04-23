package Project3;

import Application.Cluster;
import Application.Similiarities;
import Application.createClusters;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Similiarities similiarities = new Similiarities();
        Hashtable<String, Double> allCosines = similiarities.getSimiliartiy("Panda Express", 10, 0);
        //System.out.println(allCosines.toString());

        Haversine haversine = new Haversine();
        GraphSerialization serialize = new GraphSerialization();
        createClusters clusters = new createClusters();
        double lat1 = 41.5007;
        double lon1 = 100;
        double lat2 = 70.6892;
        double lon2 = 0;


        System.out.println(haversine.getHaversine(lat1, lon1, lat2, lon2));
        Graph graph = new Graph();
        Graph graph1 = new Graph();
        //graph.initailizeArrays();
        //graph.printAll();
        //graph.initalizeNodes();
        //graph.printAllNodes();
        //graph.initializeEdges();
        //graph.printAllWithEdges();
        //serialize.writeGraph(graph);
        graph1 = serialize.readGraph();
        graph1.printAllWithEdges();
        ArrayList<Cluster> allClusters = clusters.create(5, graph1.getAllCosine());
        ArrayList<Double> allMedoids = new ArrayList<>();
        for(int i = 0;i < allClusters.size(); i++)
        {
            System.out.println(allClusters.get(i).getMedoid());
            allMedoids.add(allClusters.get(i).getMedoid());
        }


        Dijkstra test = new Dijkstra();
        PathNode hello = new PathNode("Living Yoga", 40.1149289 ,-88.2385342);
        Disjoint joint = new Disjoint();
        System.out.println(joint.findDisjointSets(graph1, test, allMedoids));
        System.out.println(test.getDisjointsCheck().size());
        /*
        while(true)
        {
            System.out.print("Enter a Node: ");
            Scanner k = new Scanner(System.in);
            int input = k.nextInt();
            if(input == -1)
            {
                break;
            }
            test.dijkstras(graph1.getGraph().get(input), graph1.getGraph(), allMedoids);
            System.out.println();
        }
        System.out.println(test.getDisjointsCheck());
        System.out.println(test.getDisjointsCheck().size());

         */



    }
}
