package Application;

import com.company.HashMapThingyMAbob;
import com.company.*;

import java.util.*;

public class Similiarities
{

    public void getSimiliartiy(String business)
    {
        readReviewHashMap read = new readReviewHashMap();
        HashMapThingyMAbob map = read.readReviewsFromFile();

        /*get the array of the words in that business names review*/
        Hashtable<String, Integer> input_business = map.get(business);
        ArrayList<String> input_array = getArrayofFrequencies(input_business);
        System.out.println(input_array);
        double max = 0;
        String compared = "";
        Hashtable<String, Integer> temp = new Hashtable<String, Integer>();


        /*loop through each node to find each tf-idf valye of each business comparison*/
        for(int i = 0; i < map.getTable().length; i++)
        {
            for(HashMapThingyMAbob.Node e = map.getTable()[i]; e != null; e = e.getNext())
            {
                if(!e.getKey().equals(business))
                {
                    ArrayList<String> compare_array = getArrayofFrequencies(e.getFreqTable());

                    /*create a set, this will be all of the keys for the calculations*/
                    Set<String> hash_set = new HashSet<String>();

                    hash_set.addAll(input_array);

                    /*create my array of vectors that will be used for cosine*/
                    ArrayList<Integer> input_tfidf = new ArrayList<Integer>();
                    ArrayList<Integer> compare_to_tfidf = new ArrayList<Integer>();

                    for (String value : hash_set)
                    {
                        compare_to_tfidf.add(e.getFreqTable().getOrDefault(value, 0));
                        input_tfidf.add(input_business.getOrDefault(value, 0));
                    }

                    Cosine cosine = new Cosine();
                    double v = cosine.cosine_similarity(input_tfidf, compare_to_tfidf);
                    if(v > max)
                    {
                        max = v;
                        compared = e.getKey();
                        temp = e.getFreqTable();

                    }
                    System.out.println(e.getKey() + " SIMILIARTY FINANLLLYYY: " + v);
                }


            }

        }

        System.out.println(business + " comapred with " + compared + " with score of " + max);
        System.out.println(business + ": " + input_business);
        System.out.println(compared + ": " + temp);


    }


    public ArrayList<String> getArrayofFrequencies(Hashtable<String, Integer> input)
    {
        ArrayList<String> array = new ArrayList<String>();
        Enumeration enu = input.keys();
        while (enu.hasMoreElements()) {
            array.add((String) enu.nextElement());
        }
        return array;
    }
}
