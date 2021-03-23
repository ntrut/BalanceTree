package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class readReviewHashMap
{
    public HashMapThingyMAbob readReviewsFromFile()
    {
        HashMapThingyMAbob map = new HashMapThingyMAbob();
        try
        {
            FileInputStream fis = new FileInputStream("myhashmap.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMapThingyMAbob) ois.readObject();
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

        return map;
    }
}
