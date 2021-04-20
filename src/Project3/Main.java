package Project3;

import Application.Similiarities;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Hashtable;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Similiarities similiarities = new Similiarities();
        Hashtable<String, Double> allCosines = similiarities.getSimiliartiy("Panda Express", 10, 0);
        //System.out.println(allCosines.toString());

        Haversine haversine = new Haversine();
        GraphSerialization serialize = new GraphSerialization();
        double lat1 = 41.5007;
        double lon1 = 100;
        double lat2 = 70.6892;
        double lon2 = 0;


        System.out.println(haversine.getHaversine(lat1, lon1, lat2, lon2));
        Graph graph = new Graph();
        Graph graph1 = new Graph();
        //graph.initailizeArrays();
        //graph.printAll();
       // graph.initalizeNodes();
        //graph.printAllNodes();
        //graph.initializeEdges();
        //graph.printAllWithEdges();
        //serialize.writeGraph(graph);
        graph1 = serialize.readGraph();
        graph1.printAllWithEdges();


        Dijkstra test = new Dijkstra();
        PathNode hello = new PathNode("Living Yoga", 40.1149289 ,-88.2385342);

        test.dijkstras(graph1.getGraph().get(0), graph1.getGraph(), 0.17149858514250882);





        //System.out.println(similiarities.getOneCosineSimiliarity("Panda Express", "Kelsey's Bar and Grill"));

        /*----------------going to need to use all of the cosine values in my "allCosines" hashtable to create the clusters, but i only need it
        * for the finding shortest path algo---------------*/


    }
}
