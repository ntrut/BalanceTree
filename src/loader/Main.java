package loader;

import java.io.*;
import java.util.Scanner;

import com.company.HashMapThingyMAbob;
import com.company.Node;
import com.company.readReviewHashMap;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Load load = new Load();
        readNode();

        /*reads from file*/
        //readReviewHashMap reviews = new readReviewHashMap();
        //HashMapThingyMAbob map = reviews.readReviewsFromFile();
        //map.printAll();


    }

    public static void readNode() throws IOException {

        Node test = new Node();
        Scanner myObj = new Scanner(System.in);
        int stuff = 0;

        while(stuff != -1)
        {
            System.out.print("Enter Node Location: ");
            stuff = myObj.nextInt();

            test = test.read(stuff);
            System.out.println("***** TESTING *******");
            test.print();
        }

    }
}
