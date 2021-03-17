package com.company;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;

public class Node
{
    private int num_of_keys;
    private boolean leaf;                                                      //0 is false, 1 is true
    private long location_in_file;
    private final ArrayList<Long> location_of_children = new ArrayList<Long>();
    private final ArrayList<String> Array_of_keys = new ArrayList<String>();        //Array of all keys
    private final ByteStuff.Node[] Array_of_nodes = new ByteStuff.Node[3];     //children nodes
    private static ByteBuffer buf;

    private static final Path path = Paths.get("/home/ntrut/IdeaProjects/BalanceTree/src/com/company/tree.txt");
    private static FileChannel indexFile;
    static {
        try {
            indexFile = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Node()
    {

    }

    public Node createNode(ByteBuffer buf) throws UnsupportedEncodingException {
        /*break up the buffer to get our information*/
        Node node = new Node();
        node.setNum_of_keys(buf.get());

        /*leaf*/
        buf.position(buf.position() + 3);
        int leaf = buf.get();
        if(leaf == 1)
            node.setLeaf(true);
        else
            node.setLeaf(false);

        /*location in file*/
        buf.position(buf.position() + 3);
        node.setLocation_in_file(buf.get());

        /*all locations of the children into a array*/
        if(!node.isLeaf())
        {
            for(int i = 0; i < node.getNum_of_keys() + 1; i++)
            {
                buf.position(buf.position() + 3);
                long num = buf.get();
                node.location_of_children.add(num);
            }
        }

        /*get my keys from the buffer*/
        /*Get the keys, businesses strings*/
        buf.position(buf.position() + 3);

        for(int i = 0; i < node.getNum_of_keys(); i++)
        {
            char s = ' ';
            String str = "";
            while(((char) buf.get()) != '\0')
            {
                buf.position(buf.position() - 1);
                s = (char) buf.get();
                //System.out.println(s);
                str = str + s;
            }
            node.addKeys(str);
            if(buf.hasRemaining())
                buf.position(buf.position() + 2);
        }

        System.out.println("Num of keys: " + node.getNum_of_keys());
        System.out.println("leaf: " + node.leaf);
        System.out.println("Location in file: " + node.getLocation_in_file());
        node.getLocation_of_children().forEach(System.out::println);
        node.Array_of_keys.forEach(System.out::println);

        return node;
    }

    public void write() throws IOException
    {

        Path path = Path.of("/home/ntrut/IdeaProjects/BalanceTree/src/com/company/tree.txt");
        try (FileChannel channel = FileChannel.open(path,
                StandardOpenOption.WRITE)) {

            /*set that index in the file to null so we can update it
             * pretty much overwriting it instead of updating*/
            ByteBuffer temp;
            temp = ByteBuffer.allocate(100);
            channel.write(temp, location_in_file * 100L);

            buf = ByteBuffer.allocate(100);

            /*num_of_children, leaf, location_in_file, location_of_children, all keys
             * offset of 2 in my bytebuffers*/
            buf.put((byte) num_of_keys);
            if(leaf)
            {
                buf.position(buf.position() + 3);
                buf.put((byte) 1);
            }
            else
            {
                buf.position(buf.position() + 3);
                buf.put((byte) 0);
            }

            /*location in file */
            buf.position(buf.position() + 3);
            buf.put((byte) location_in_file);

            /*all locations of the children*/
            for (long location_of_child : location_of_children) {
                buf.position(buf.position() + 3);
                buf.put((byte) location_of_child);
            }

            /*all keys*/
            for (String array_of_key : Array_of_keys) {
                buf.position(buf.position() + 3);
                buf.put(array_of_key.getBytes(StandardCharsets.UTF_8));
            }

            buf.flip();
            channel.write(buf, location_in_file * 100L);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addKeys(String business)
    {
        Array_of_keys.add(business);
        Collections.sort(this.Array_of_keys);
    }

    public void incNumKeys()
    {
        this.num_of_keys++;
    }

    public void addLocations(long loc)
    {
        location_of_children.add(loc);
    }

    /*read from the file and return that node from the file??*/
    public Node read(long pos) throws IOException {
        buf = ByteBuffer.allocate(100);
        indexFile.read(buf,pos * 100L);
        buf.flip();
        //System.out.println(Arrays.toString(buf.array()));
        return createNode(buf);
    }

    public int getNum_of_keys() {
        return num_of_keys;
    }

    public void setNum_of_keys(int num_of_keys) {
        this.num_of_keys = num_of_keys;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public long getLocation_in_file() {
        return location_in_file;
    }

    public void setLocation_in_file(int location_in_file) {
        this.location_in_file = location_in_file;
    }

    public ArrayList<Long> getLocation_of_children() {
        return location_of_children;
    }

    public void setLocation_in_file(long location_in_file) {
        this.location_in_file = location_in_file;
    }

    public ArrayList<String> getArray_of_keys() {
        return Array_of_keys;
    }

    public ByteStuff.Node[] getArray_of_nodes() {
        return Array_of_nodes;
    }
}
