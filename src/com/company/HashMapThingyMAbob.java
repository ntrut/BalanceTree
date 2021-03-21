package com.company;

import java.io.Serializable;
import java.util.Hashtable;

public class HashMapThingyMAbob implements Serializable
{
   private final int size = 8;
   private Node[] table = new Node[size];
   private int count = 0;

   static class Node
   {
       private String key;
       private Hashtable<String, Integer> freqTable = new Hashtable<String, Integer>();
       private Node next;


       public String getKey() {
           return key;
       }
       public void setKey(String key) {
           this.key = key;
       }
       public Hashtable<String, Integer> getFreqTable() {
           return freqTable;
       }

       public void setFreqTable(Hashtable<String, Integer> freqTable) {
           this.freqTable = freqTable;
       }
       public Node(String business, Hashtable<String, Integer> freqTable) {
           this.key = business;
           this.freqTable = freqTable;
       }
       public Node getNext() {
           return next;
       }
       public void setNext(Node next) {
           this.next = next;
       }
   }

   public void put(String key, String review)
   {
       int asciiCODE = key.hashCode();     //sum of ASCII codes
       int index = asciiCODE & (table.length - 1);   // gets the index number of the string

       for(Node e = table[index]; e != null; e = e.getNext())
       {
           /*check if there already is the same key*/
           if(key.equals(e.getKey()))
           {
               return;
           }
       }
       count++;
       table[index] = new Node(key, getFrequencies(review));
       /*RESIZE*/
       if(((double) count / (double)table.length) > 0.75)
       {
          resize();
       }
   }

    /*break up the review text into a array
     * then record it into the frequency table in a node*/
    public Hashtable<String, Integer> getFrequencies(String review)
    {
        Hashtable<String, Integer> freqTable = new Hashtable<String, Integer>();

        review.replace(".", "");
        String[] words = review.split("\\W");
        for (String word : words)
        {
            if(freqTable.containsKey(word))
            {
                int i = freqTable.get(word) + 1;
                freqTable.put(word, i);
            }
            else
                freqTable.put(word, 1);

        }
        return freqTable;

    }

    /*Returns the Frequency table of that string of where the key is in the hashtable
     * returns -1 if not found*/
    public Hashtable<String, Integer> get(String key)
    {
        int asciiCODE = key.hashCode();     //sum of ASCII codes
        int index = asciiCODE & (table.length - 1);   //gets the index number of the string

        for(Node e = table[index]; e != null; e = e.getNext())
        {
            if(e.getKey().equals(key))
            {
                return e.getFreqTable();
            }
        }
        return null;
    }

    /*Resize the array*/
    public void resize()
    {
        Node[] oldtable = table;
        Node[] newTable = new Node[oldtable.length * 2];

        for(int i = 0; i < table.length; i++)
        {
            for(Node e = table[i]; e != null; e = e.getNext())
            {
                int h = e.getKey().hashCode();
                int index = h & (newTable.length - 1);
                newTable[index] = new Node(e.getKey(), e.getFreqTable());
            }

        }
        table = newTable;
    }

    /*print out all of the frequency tables*/
    public void printAll()
    {
        for(int i = 0; i < table.length; i++)
        {
            for(Node e = table[i]; e != null; e = e.getNext())
            {
                System.out.println("{" +  i + "}" + e.getKey() + ": " + e.getFreqTable());
            }

        }
    }

}
