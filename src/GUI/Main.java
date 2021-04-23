package GUI;

import Application.Cluster;
import Application.createClusters;
import Project3.Graph;
import Project3.GraphSerialization;

import java.io.IOException;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        GraphSerialization serialize = new GraphSerialization();
        createClusters clusters = new createClusters();

        Graph graph1 = new Graph();

        graph1 = serialize.readGraph();
        graph1.printAllWithEdges();
        ArrayList<Cluster> allClusters = clusters.create(10, graph1.getAllCosine());
        ArrayList<Double> allMedoids = new ArrayList<>();
        for(int i = 0;i < allClusters.size(); i++)
        {
            System.out.println(allClusters.get(i).getMedoid());
            allMedoids.add(allClusters.get(i).getMedoid());
        }
        Frame2 frame = new Frame2(graph1, allMedoids);
    }
}
