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

        GraphSerialization serialize = new GraphSerialization();
        createClusters clusters = new createClusters();

        //Graph graph = new Graph();
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
        Disjoint joint = new Disjoint();
        //System.out.println(joint.findDisjointSets(graph1, test, allMedoids));
        //System.out.println(test.getDisjointsCheck().size());

        while(true)
        {
            System.out.print("Enter a Node: ");
            Scanner k = new Scanner(System.in);
            int input = k.nextInt();
            if(input == -1)
            {
                break;
            }
            System.out.println(test.dijkstras(graph1.getGraph().get(input), graph1.getGraph(), allMedoids));
            System.out.println();
        }
        System.out.println(test.getDisjointsCheck());
        System.out.println(test.getDisjointsCheck().size());



    }
}
