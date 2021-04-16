package Project3;

import java.io.*;

public class GraphSerialization
{
    public void writeGraph(Graph graph)
    {
        String filename = "graph.ser";

        try{
            //Saving of object in a file

            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(graph);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Graph readGraph()
    {
        String filename = "graph.ser";
        Graph graph = new Graph();

        try{
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

             graph = (Graph) in.readObject();

            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return graph;
    }
}
