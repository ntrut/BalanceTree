package Project3;

import java.util.ArrayList;

public class Disjoint
{
    public int findDisjointSets(Graph graph, Dijkstra dijkstra, ArrayList<Double> allMedoids)
    {
        int disjointSets = 0;
        dijkstra.dijkstras(graph.getGraph().get(0), graph.getGraph(), allMedoids);
        for(int i = 1; i < graph.getGraph().size(); i++)
        {
            if(!dijkstra.getDisjointsCheck().containsKey(graph.getGraph().get(i)))
            {
                dijkstra.dijkstras(graph.getGraph().get(i), graph.getGraph(), allMedoids);
                disjointSets = disjointSets + 1;
            }

        }
        return disjointSets;
    }
}
