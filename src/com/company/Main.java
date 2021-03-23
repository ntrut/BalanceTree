package com.company;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;


public class Main {

    public static void main(String[] args) throws IOException
    {

        /*
        String[] names_array = new String[10000];
        int index = 0;
        int check = 0;
        try{
            File file = new File("/home/ntrut/IdeaProjects/BalanceTree/src/com/company/Businesses_names.txt");
            Scanner read = new Scanner(file);

            while(read.hasNextLine())
            {
                Pattern p = Pattern.compile("^[a-zA-Z0-9_ &-'+,.\\\"-/!|@:;]*$");

                String line = read.nextLine();
                Matcher m = p.matcher(line);
                boolean b;
                if(b = m.matches())
                {
                    check++;
                    names_array[index] = line;
                }
                index++;
            }
            read.close();
        }catch(FileNotFoundException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }



        BalanceTree tree = new BalanceTree();
        tree.createTree(names_array[0]);

        for(int i = 1; i < names_array.length; i++)
        {

            if(names_array[i] != null)
            {

                tree.insert(names_array[i]);
            }

        }

         */

        /*
        tree.createTree("Buffalo Wild Wings");
        tree.insert("American");
        tree.insert("Chicfila");
        tree.insert("Chiptole");
        tree.insert("Taco Bell");
        tree.insert("Burger King");
        tree.insert("Lucky Charms");
        tree.insert("Phar");
        tree.insert("Apple");
        tree.insert("Wipp");
        tree.insert("Mayo");
        tree.insert("Aaaa");
        tree.insert("Nest");
        tree.insert("Jack");
        tree.insert("Ashe");
        tree.insert("ABCD");
        tree.insert("Naza");
        tree.insert("Dunk");
        tree.insert("Koko");
        tree.insert("Dort");
        tree.insert("Earth");
        tree.insert("Look");
        tree.insert("Zebra");
        tree.insert("Octo");
        tree.insert("Kyyy");
        tree.insert("Kill");
        tree.insert("Line");
        tree.insert("Last");
        tree.insert("Aook");
        tree.insert("Abea");
        tree.insert("Akkk");
        tree.insert("Acorn");
        tree.insert("Cake");
        tree.insert("Whyy");
        read();
        */

       // tree.printRoot();
        //System.out.println("TOTAL STRINGS: " + check);
        //readNode();

        /*Aook Abea Akkk Acorn*/
    }

    public static void read() throws IOException {
        Path path = Path.of("/home/ntrut/IdeaProjects/BalanceTree/src/com/company/tree.txt");
        try (FileChannel channel = FileChannel.open(path,
                StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(200);
            channel.read(buffer, 5);
            buffer.flip();

            System.out.println(Arrays.toString(buffer.array()));


        }
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
