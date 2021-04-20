package Project3;

import Application.Similiarities;
import com.company.HashMapThingyMAbob;
import com.company.readReviewHashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;

public class Graph implements Serializable
{
    ArrayList<PathNode> graph = new ArrayList<PathNode>();
    String[] business_names = new String[10000];
    Double[] latitudes_array = new Double[10000];
    Double[] longitudes_array = new Double[10000];

    /*This adds the nodes to the graph*/
    public void initalizeNodes()
    {
        Haversine havesine = new Haversine();
        int skip = 0;
        double latitude1 = 0;
        double longitude1 = 0;
        for(int i = 0; i < business_names.length; i++)
        {
                latitude1 = latitudes_array[i];
                longitude1 = longitudes_array[i];

        }

        /*create a temp so i can loop thru all of the keys and if we have the business name, we create a graph node
        * the reason i do this is because we dont have any duplicate businesses*/
        Hashtable<String, Double> temp;
        for(int i = 0; i < 10000; i++)
        {
            PathNode newNode = new PathNode(business_names[i], latitudes_array[i], longitudes_array[i]);
            graph.add(newNode);
        }
    }

    public ArrayList<PathNode> copy(ArrayList<PathNode> e)
    {
        return e = new ArrayList<PathNode>(this.graph);
    }

    /*Find the nodes closest neighbors and create edges
    * then once we find their edges, calculate the cosine(weight)*/
    public void initializeEdges()
    {
        readReviewHashMap read = new readReviewHashMap();
        HashMapThingyMAbob map = read.readReviewsFromFile();

        ArrayList<PathNode> clone = new ArrayList<PathNode>();
        /*create cloned array*/
        clone.addAll(graph);

        for(int i = 0; i < clone.size(); i++)
        {
            findClosest4neighbors(clone.get(i), map);
            System.out.println(i);
        }
        /*so everything is in order like the text files*/
        graph = clone;
    }

    public void findClosest4neighbors(PathNode node, HashMapThingyMAbob map)
    {
        ArrayList<PathNode> temp;
        temp = graph;
        Haversine haversine = new Haversine();
        Similiarities similiarities = new Similiarities();
        for(int i = 0; i < temp.size(); i++)
        {
            //System.out.println("here");
            double distance = haversine.getHaversine(node.getLatitude(), node.getLongitude(), temp.get(i).getLatitude(), temp.get(i).getLongitude());
            temp.get(i).setDistance(distance);
        }

        Collections.sort(temp);
        //printAllNodes();

        /*find the 4 closest nodes*/
            for(int i = 0; i < temp.size(); i++)
            {
                if(temp.get(i).getDistance() != 0)
                {
                    node.addEdge(new Edge(temp.get(i), node, similiarities.getOneCosineSimiliarity(node.getBusiness(), temp.get(i).getBusiness(), map)));

                    if(node.getNeighbors().size() == 4)
                    {
                        break;
                    }
                }
            }
    }

    /*Initializes the arrays with business names, longitudes and latitudes*/
    public void initailizeArrays() throws FileNotFoundException {
        File businesses = new File("/home/ntrut/IdeaProjects/BalanceTree/src/com/company/Businesses_names.txt");
        File latitudes = new File("/home/ntrut/IdeaProjects/BalanceTree/src/latitude.txt");
        File longitudes = new File("/home/ntrut/IdeaProjects/BalanceTree/src/longitude.txt");

        Scanner read_businesses = new Scanner(businesses);
        Scanner read_latitudes = new Scanner(latitudes);
        Scanner read_longitudes = new Scanner(longitudes);

        /*initialize the arrays*/
        int index = 0;
        while(read_businesses.hasNext())
        {
            String str = read_businesses.nextLine();
            business_names[index] = str;
            index++;
        }

        int index2 = 0;
        while(read_latitudes.hasNext())
        {
            double d = Double.parseDouble(read_latitudes.nextLine());
            latitudes_array[index2] = d;
            index2++;
        }

        int index3 = 0;
        while(read_longitudes.hasNext())
        {
            double d = Double.parseDouble(read_longitudes.nextLine());
            longitudes_array[index3] = d;
            index3++;
        }
    }

    public void printAll()
    {
        for(int i = 0; i < 10000; i++)
        {
            System.out.println(i + ", " + business_names[i] + " latitude: " + latitudes_array[i] + ", longitude: " + longitudes_array[i] + "\n");
        }
    }

    public void printAllNodes()
    {
        for(int i = 0; i < graph.size(); i++)
        {
            System.out.println(i + ", " + graph.get(i).getBusiness() + " distance: " + graph.get(i).getDistance() + ", Latitude: " + graph.get(i).getLatitude() + ", Longitude: " + graph.get(i).getLongitude());
        }
    }

    public void printAllWithEdges()
    {
        for(int i = 0; i < graph.size() - 9900; i++)
        {
            System.out.print(i + ", " + graph.get(i).getBusiness() +  ", Latitude: " + graph.get(i).getLatitude() + ", Longitude: " + graph.get(i).getLongitude() + " EDGES: ");
            for(int j = 0; j < graph.get(i).getNeighbors().size(); j++)
            {
                System.out.print("[Parent Node: " + graph.get(i).getNeighbors().get(j).getParent().getBusiness() + ", Destination Node: " + graph.get(i).getNeighbors().get(j).getDestination().getBusiness() + ", Weight: " + graph.get(i).getNeighbors().get(j).getWeight() + "]");
            }
            System.out.println("");
        }
    }

    /*------------GETTERS & SETTERS-----------*/

    public ArrayList<PathNode> getGraph()
    {
        return graph;
    }

    public void setGraph(ArrayList<PathNode> graph) {
        this.graph = graph;
    }
}
