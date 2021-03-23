package Application;

import java.util.ArrayList;

public class Cosine
{


    public double  cosine_similarity(ArrayList<Integer> input_tfidf, ArrayList<Integer> compare_to)
    {
        double similarity;

        similarity = dot_product(input_tfidf, compare_to) / (magnitude(input_tfidf) * magnitude(compare_to));

        return similarity;
    }

    public double dot_product(ArrayList<Integer> input_tfidf, ArrayList<Integer> compare_to)
    {
        double total = 0;

        for(int i = 0; i < input_tfidf.size(); i++)
        {
            total += input_tfidf.get(i) * compare_to.get(i);
        }

        return total;
    }

    public double magnitude(ArrayList<Integer> array)
    {
        double mag = 0;

        for(int i = 0; i < array.size(); i++)
        {
            mag += Math.pow(array.get(i), 2);
        }
        return Math.sqrt(mag);
    }
}
