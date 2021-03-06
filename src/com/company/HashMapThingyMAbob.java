package com.company;

import java.util.Hashtable;
import java.util.Locale;
import java.util.Set;

public class HashMapThingyMAbob implements java.io.Serializable
{
   private final int size = 8;
   private Node[] table = new Node[size];
   private int count = 0;

   public static class Node implements java.io.Serializable
   {
       private String key;
       private Hashtable<String, Integer> freqTable = new Hashtable<String, Integer>();
       private Node next;

        public int total_frequncies()
        {
            int total = 0;
            Set<String> keys = freqTable.keySet();
            for(String key: keys)
            {
                total = total + freqTable.get(key);
            }
            return total;
        }

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
       public Node(String business, Hashtable<String, Integer> freqTable, Node next) {
           this.key = business;
           this.freqTable = freqTable;
           this.next = next;
       }
       public Node getNext() {
           return next;
       }
       public void setNext(Node next) {
           this.next = next;
       }
   }

    public int getSize() {
        return size;
    }

    public Node[] getTable() {
        return table;
    }

    public void setTable(Node[] table) {
        this.table = table;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
       table[index] = new Node(key, getFrequencies(review), table[index]);
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

        String[] words = review.split("\\W");
        for (String word : words)
        {
            if(!word.equals(""))
            {
                word = word.toLowerCase();
                if(freqTable.containsKey(word))
                {
                    int i = freqTable.get(word) + 1;
                    freqTable.put(word, i);
                }
                else
                    freqTable.put(word, 1);
            }


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
                newTable[index] = new Node(e.getKey(), e.getFreqTable(), newTable[index]);
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
