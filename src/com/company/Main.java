package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException
    {

        /*
       Node node = new Node();
        Node node2 = new Node();
        Node node3= new Node();
        Node node4= new Node();

        node.setNum_of_keys(2);
        node.setLeaf(false);
        node.setLocation_in_file(0);
        node.setLocation_of_parent(-1);
        node.addKeys("McDonalds");
        node.addKeys("KFC");
        node.addLocations(1);
        node.addLocations(2);
        node.addLocations(3);
        node.write();
        node.read(0);
        BalanceTree tree = new BalanceTree();



        System.out.println("****************************************************");

        node2.setNum_of_keys(1);
        node2.setLeaf(true);
        node2.setLocation_of_parent(-1);
        node2.setLocation_in_file(1);
        node2.addKeys("Zebra");
        node2.write();

        System.out.println("*****************************************************");

        node3.setNum_of_keys(1);
        node3.setLeaf(false);
        node3.setLocation_of_parent(-1);
        node3.setLocation_in_file(2);
        node3.addKeys("Dance Club Ukr");
        node3.write();

        node4.setNum_of_keys(2);
        node4.setLeaf(false);
        node4.setLocation_of_parent(-1);
        node4.setLocation_in_file(3);
        node4.addKeys("okay");
        node4.addKeys("target");
        node4.write();



        tree.sortChildren(node);
        read();

         */

        BalanceTree tree = new BalanceTree();
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
        read();
        tree.printRoot();
        readNode();

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
        test = test.read(4);
        System.out.println("***** TESTING *******");
        test.print();

    }

}
