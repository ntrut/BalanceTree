package loader;

import java.io.*;
import java.util.Scanner;

import com.company.Calculate;
import com.company.HashMapThingyMAbob;
import com.company.Node;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        //Load load = new Load();
        //readNode();

        /*reads from file*/
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
        map.printAll();

        Calculate cal = new Calculate();
        System.out.println(cal.documents_with_term("pizza", map));


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
