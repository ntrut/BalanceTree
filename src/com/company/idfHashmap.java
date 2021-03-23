package com.company;

import java.io.*;
import java.util.Hashtable;

public class idfHashmap
{


    public void write(Hashtable<String, Integer> idf)
    {
        try
        {
            FileOutputStream fos =
                    new FileOutputStream("idf.ser", false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(idf);
            oos.close();
            fos.close();
            System.out.println("Serialized HashMap data is saved in idf.ser");
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public Hashtable<String, Integer> readidf()
    {
        Hashtable<String, Integer> idf = new Hashtable<String,Integer>();
        try
        {
            FileInputStream fis = new FileInputStream("idf.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            idf = (Hashtable) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }catch(ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        return idf;
    }

    public void addWord(Hashtable<String, Integer> idf, String word, int wordCount)
    {
        if(!idf.containsKey(word))
        {
            idf.put(word,wordCount);
        }
    }

}
