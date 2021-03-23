package Application;

import com.company.HashMapThingyMAbob;
import com.company.*;

import java.util.Hashtable;

public class Calculate
{
    public double IF(int total_terms, int word_freq)
    {
        //System.out.println("IF: " + (double) word_freq / (double)total_terms);
        return (double) word_freq / total_terms;

    }

    public double IDF(double term_in_documents)
    {
        //System.out.println("IDF docuemnt: " + documents_with_term(word));

        return Math.log(10000.0 / term_in_documents);
    }

    /*Takes in a word and returns the number of documents where the word is
    for calculating idf
    * log_e(Total number of documents / Number of documents with term in it).*/
    public int documents_with_term(String word)
    {
        readReviewHashMap read = new readReviewHashMap();
        HashMapThingyMAbob table = read.readReviewsFromFile();
        idfHashmap idf = new idfHashmap();
        Hashtable<String, Integer> temp = idf.readidf();
        int total_word_in_document = 0;

        if(temp.getOrDefault(word, 0) > 0)
        {
            return temp.get(word);
        }

        for(int i = 0; i < table.getTable().length; i++)
        {
            for(HashMapThingyMAbob.Node e = table.getTable()[i]; e != null; e = e.getNext())
            {

                if(e.getFreqTable().containsKey(word))
                {
                    total_word_in_document++;
                }
            }
        }
        idf.addWord(temp,word,total_word_in_document);
        idf.write(temp);
        System.out.println(idf);
        return total_word_in_document;
    }
    

}
