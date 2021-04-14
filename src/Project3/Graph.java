package Project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Graph
{
    ArrayList<PathNode> graph = new ArrayList<PathNode>();
    String[] business_names = new String[10000];
    Double[] latitudes_array = new Double[10000];
    Double[] longitudes_array = new Double[10000];

    /*This adds the nodes to the graph*/
    public void initalizeNodes(String business, Hashtable<String, Double> test)
    {
        Haversine havesine = new Haversine();
        int skip = 0;
        double latitude1 = 0;
        double longitude1 = 0;
        for(int i = 0; i < business_names.length; i++)
        {
            if(business.equals(business_names[i]))
            {
                skip = i;
                latitude1 = latitudes_array[i];
                longitude1 = longitudes_array[i];
                break;
            }
        }

        /*create a temp so i can loop thru all of the keys and if we have the business name, we create a graph node
        * the reason i do this is because we dont have any duplicate businesses*/
        Hashtable<String, Double> temp;
        temp = test;
        for(int i = 0; i < 10000; i++)
        {
            if(i != skip)
            {
                /*we skip the business that the user inputs
                * we create a new node with the havesine and initalize the node with business and distance*/
                if(temp.containsKey(business_names[i]))
                {
                    double newDistance = havesine.getHaversine(latitude1, longitude1, latitudes_array[i], longitudes_array[i]);
                    PathNode newNode = new PathNode(business_names[i], newDistance, temp.get(business_names[i]));
                    graph.add(newNode);
                    temp.remove(business_names[i]);
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
            System.out.println(i + ", " + graph.get(i).getBusiness() + " distance: " + graph.get(i).getDistance() + ", Cosine: " + graph.get(i).getSimilarity());
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
