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

        node.setNum_of_children(2);
        node.setLeaf(false);
        node.setLocation_in_file(0);
        node.addKeys("Mc Donalds");
        node.addKeys("KFC");
        node.addLocations(1);
        node.addLocations(2);
        node.write();
        Node temp = node.read(0);


        System.out.println("****************************************************");

        node2.setNum_of_children(1);
        node2.setLeaf(true);
        node2.setLocation_in_file(1);
        node2.addLocations(4);
        node2.addKeys("Panera Bread");
        node2.write();
        Node temp2 = node2.read(1);

        System.out.println("*****************************************************");

        node3.setNum_of_children(3);
        node3.setLeaf(false);
        node3.setLocation_in_file(0);
        node3.addKeys("Dance Club Ukr");
        node3.addKeys("Wegmans");
        node3.addKeys("The Autozone");
        node3.addLocations(1);
        node3.addLocations(2);
        node3.addLocations(3);
        node3.write();
        Node temp3 = node3.read(0);


        read();

         */

        BalanceTree tree = new BalanceTree();
        tree.createTree("McDonalds");
        read();


    }

    public static void read() throws IOException {
        Path path = Path.of("/home/ntrut/IdeaProjects/BTreesAssignment/src/com/company/tree.txt");
        try (FileChannel channel = FileChannel.open(path,
                StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(100);
            channel.read(buffer, 0);
            buffer.flip();

            System.out.println(Arrays.toString(buffer.array()));


        }
    }


}
