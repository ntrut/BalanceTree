package Application;

import java.util.ArrayList;

public class Swapping
{
    public ArrayList<Cluster> calculateAllSums(ArrayList<Cluster> clusters)
    {
        for(int i =0; i < clusters.size(); i++)
        {
            clusters.get(i).calculateTotalDistance();
        }
        return clusters;
    }

    public double calculateDistance(double input, double mediod)
    {
        return Math.abs(mediod - input);
    }

    public ArrayList<Cluster> swapCheck(int index, ArrayList<Cluster> clusters, ArrayList<Cluster> orginial)
    {
        /*reassign all of the nonmedoids*/
        clusters = reAssignNonMedoids(clusters);
        clusters = calculateAllSums(clusters);

        if(clusters.get(index).getTotal_sum_distance() < orginial.get(index).getTotal_sum_distance())
        {
            //System.out.println("Swapped! new cluster " + clusters.get(index).getTotal_sum_distance() + " orginial cluster sum " + orginial.get(index).getTotal_sum_distance());
            return clusters;
        }


        return orginial;
    }

    public ArrayList<Cluster> reAssignNonMedoids(ArrayList<Cluster> clusters)
    {

        ArrayList<Double> newArray = new ArrayList<Double>();
        for(int i = 0; i < clusters.size(); i++)
        {
            newArray.addAll(clusters.get(i).getNonmedoids());
            clusters.get(i).getNonmedoids().clear();
            clusters.get(i).calculateTotalDistance();
        }

        int index = 0;

        for(int i = 0; i < newArray.size(); i++)
        {
            int track = 0;
            double min = calculateDistance(newArray.get(i), clusters.get(0).getMedoid());
            for(int j = 0; j < clusters.size(); j++)
            {
                double compare = calculateDistance(newArray.get(i), clusters.get(j).getMedoid());
                if(compare < min)
                {
                    min = compare;
                    index = j;
                }
                track++;

            }
            clusters.get(index).getNonmedoids().add(newArray.get(i));
        }

        return clusters;
    }


}
