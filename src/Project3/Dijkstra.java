package Project3;

import java.nio.file.Path;
import java.util.*;

public class Dijkstra
{
        public void dijkstras(PathNode node, ArrayList<PathNode> graph, double CosineTest)
        {
            HashMap<PathNode, Double> Distances = new HashMap<>();
            ArrayList<PathNode> visited = new ArrayList<>();
            ArrayList<PathNode> unvisited = new ArrayList<>(graph);
            ArrayList<PathNode> queue = new ArrayList<>();

            /*set all distances to max double, set the current node to a distance of 0*/
            for(int i = 0; i < graph.size(); i++)
            {
                if(graph.get(i).getLongitude() == node.getLongitude() && graph.get(i).getLatitude() == node.getLatitude())
                {
                    Distances.put(graph.get(i), 0.0);
                }
                else
                    Distances.put(graph.get(i), Double.MAX_VALUE);
            }

            /*find shortest path*/
            PathNode current = node;
            while(unvisited.size() > 0)
            {
                System.out.println("We are Currently at " + current.getBusiness());
                for(int i = 0; i < current.getNeighbors().size(); i++)
                {

                    /*check to see if we found the cosine value equal to the destination cosine value*/
                    if(current.getNeighbors().get(i).getWeight() == CosineTest)
                    {
                        /*we found the destination*/
                        System.out.println(current.getNeighbors().get(i).getDestination().getBusiness() + " found! with a path of " + Distances.get(current.getNeighbors().get(i).getDestination()));
                        return;
                    }
                    /*check if that node has been already visited*/
                    if(!visited.contains(current.getNeighbors().get(i).getDestination()))
                    {
                        /*check to see if its max double, if it is then set it to 0*/
                        if(Distances.get(current.getNeighbors().get(i).getDestination()) == Double.MAX_VALUE)
                        {
                            Distances.put(current.getNeighbors().get(i).getDestination(), 0.0);
                        }
                        Distances.put(current.getNeighbors().get(i).getDestination(), Distances.get(current.getNeighbors().get(i).getDestination()) + current.getNeighbors().get(i).getWeight());
                        //System.out.println(Distances.get(current.getNeighbors().get(i).getDestination()));
                    }
                }

                visited.add(current);
                unvisited.remove(current);

                /*find the shortest path in those edges and go to that node?*/
                PathNode min = null;
                int counter = 0;
                while(counter != 4)
                {
                    min = current.getNeighbors().get(counter).getDestination();
                    if(!visited.contains(min))
                    {
                        break;
                    }
                    counter++;
                }



                for(int i = 1; i < current.getNeighbors().size(); i++)
                {
                    PathNode compare = current.getNeighbors().get(i).getDestination();
                    //System.out.println(Distances.get(min) + " compared to " + Distances.get(compare));
                    if(Distances.get(min) >= Distances.get(compare))
                    {
                        if(!visited.contains(compare))
                            min = compare;
                    }
                }
                if(!visited.contains(min))
                    current = min;
            }
            /*
            Queue<PathNode> lol = new PriorityQueue<>();
            PathNode test1 = new PathNode("Hello", 1,1);
            PathNode test2 = new PathNode("Hello", 1,1);
            Distances.put(test1, 0.0);
            Distances.put(test2, 0.1);
            lol.add(test1);
            lol.add(test2);
            System.out.println(lol);
            lol.poll();
            System.out.println(lol);
            System.out.println(Distances.get(test1));

             */
        }

}
