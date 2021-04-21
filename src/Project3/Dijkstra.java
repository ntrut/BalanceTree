package Project3;

import java.nio.file.Path;
import java.util.*;

public class Dijkstra
{
        public void dijkstras(PathNode node, ArrayList<PathNode> graph, double CosineTest)
        {
            PathNode target = null;
            HashMap<PathNode, Double> Distances = new HashMap<>();
            ArrayList<PathNode> visited = new ArrayList<>();
            ArrayList<PathNode> unvisited = new ArrayList<>(graph);
            HashMap<PathNode, PathNode> PreviousNode = new HashMap<>();
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
                        target = current.getNeighbors().get(i).getDestination();
                        System.out.println("FOUND!");
                        /*
                        double display = current.getNeighbors().get(i).getWeight();
                        //System.out.println(Distances.get(current));
                        //System.out.println(current.getNeighbors().get(i).getWeight());
                       Distances.put(current.getNeighbors().get(i).getDestination(), Distances.get(current) + current.getNeighbors().get(i).getWeight());

                        current.getNeighbors().get(i).getDestination().setParent(current);
                        current = current.getNeighbors().get(i).getDestination();
                        System.out.println(current.getBusiness() + " found! with a path of " + Distances.get(current) + " with the cosine of " + display);


                        printPath(current);
                        return;

                         */

                    }

                    /*check if that node has been already visited*/
                    //if(!visited.contains(current.getNeighbors().get(i).getDestination()))
                    //{

                        /*check to see if the new value is smaller than the current value
                        * if the new value is smaller, then we replace the destination node with the smaller value
                        * meaning we found a shorter path to that node*/
                        double check = Distances.get(current) + current.getNeighbors().get(i).getWeight();
                        if(check < Distances.get(current.getNeighbors().get(i).getDestination()))
                        {
                            /*check to see if its max double, if it is then set it to 0*/
                            if(Distances.get(current.getNeighbors().get(i).getDestination()) == Double.MAX_VALUE)
                            {
                                Distances.put(current.getNeighbors().get(i).getDestination(), 0.0);
                            }

                            Distances.put(current.getNeighbors().get(i).getDestination(), Distances.get(current) + current.getNeighbors().get(i).getWeight());
                            PreviousNode.put(current.getNeighbors().get(i).getDestination(), current);
                        }
                        //System.out.println(Distances.get(current));
                        //System.out.println(current.getNeighbors().get(i).getWeight());
                        //System.out.println(Distances.get(current.getNeighbors().get(i).getDestination()));
                    //}
                }

                visited.add(current);
                unvisited.remove(current);

                while(backTrace(current, visited))
                {
                    /*all paths have been visited and we are back at our original node with no more paths to visit*/
                    if(current == node)
                    {
                        if(target == null)
                        {
                            System.out.println("No shortest path found");
                        }
                        else
                        {
                            printPath(node, target, PreviousNode);
                        }

                        return;
                    }
                    current = PreviousNode.get(current);
                }
                    /*finds the first edge that has not been visited yet so we can start comparing from that edge*/
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

                    /*find the edge that has the shortest path and go there*/
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
                    {
                        current = min;
                    }

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

        /*if all edges destination already been visited, then return true
        * else we just return false*/
        public boolean backTrace(PathNode node, ArrayList<PathNode> visited)
        {
            for(int i =0;i < node.getNeighbors().size(); i++)
            {
                if(!visited.contains(node.getNeighbors().get(i).getDestination()))
                {
                    return false;
                }
            }

            return true;
        }

        public void printPath(PathNode original, PathNode target, HashMap<PathNode, PathNode> PreviousNode)
        {
            while(true)
            {
                if(target == null)
                {
                    break;
                }

                System.out.print(target.getBusiness() + " <--- ");
                target = PreviousNode.get(target);
            }
            System.out.println("BEGIN");
        }

}
