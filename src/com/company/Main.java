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

        //BalanceTree tree = new BalanceTree();
        //tree.printRoot();
        //System.out.println("TOTAL STRINGS: " + check);
        readNode();

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
