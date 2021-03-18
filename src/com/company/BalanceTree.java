package com.company;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class BalanceTree
{
    private Node root;
    private long locations = 0;

    public void createTree(String businessName) throws IOException {
        root = new Node();
        root.setLeaf(true);
        root.addKeys(businessName);
        root.incNumKeys();
        root.setLocation_of_parent(-1);     //no parent
        root.setLocation_in_file(locations);
        root.write();
        root.read(0);
    }
    public long newLocation()
    {
        locations = locations + 1;
        return locations;
    }
    public void insert(String business) throws IOException {

        /*if root is a leaf, then just add the keys to the root*/
        if(root.isLeaf())
        {
            root.addKeys(business);
            root.incNumKeys();

            /*the beginning of teh tree*/
            if(root.getNum_of_keys() == 3 && root.getLocation_of_children().size() == 0)
            {
                splitRoot();
            }
            root.write();
            root.print();
        }
        else
        {

            Node temp = root;
            while(!temp.isLeaf())
            {
                /*get which node it is going into*/
                if(temp.getNum_of_keys() == 1)
                {
                    /*left is 0
                    * right is 1*/
                    int s = getSide(temp, business);
                    temp = temp.read(temp.getLocation_of_children().get(s));
                }
                else
                {
                    /*left is 0
                    * middle is 1
                    * right is 2*/
                    int s = getSide(temp, business);
                    temp = temp.read(temp.getLocation_of_children().get(s));
                }

            }
            temp.addKeys(business);
            temp.incNumKeys();
            temp.write();
            //temp.print();
            if(temp.getNum_of_keys() == 3)
            {
                splitChild(temp);
            }
        }

    }

    public void splitChild(Node child) throws IOException {
        /*split leaf node*/
        if(child.isLeaf())
        {
            Node temp = child.read(child.getLocation_of_parent());

                System.out.println("EFSDFSFSDF");
                temp.addKeys(child.getArray_of_keys().remove(1));
                temp.incNumKeys();

                /*create new node*/
                Node newNode = new Node();
                newNode.setLocation_in_file(newLocation());
                newNode.addKeys(child.getArray_of_keys().remove(1));

                /*set num of keys*/
                newNode.setNum_of_keys(1);
                child.setNum_of_keys(1);

                /*set leaf*/
                newNode.setLeaf(true);

                /*set parent of new node*/
                newNode.setLocation_of_parent(temp.getLocation_in_file());

                /*add new node to the parent children*/
                temp.addLocations(newNode.getLocation_in_file());

                newNode.write();
                child.write();
                sortChild(temp);
                if(temp.getLocation_of_parent() == -1)
                    root = temp;

                if(temp.getArray_of_keys().size() == 3)
                    splitChild(temp);



                System.out.println("********************NEW NODE AFTER SPLITTING LEAF *********************");
                newNode.print();
                child.print();



        }
        else if(child.getLocation_of_parent() == -1)    //has no parent, which means its the root with children
        {
            if(child.getNum_of_keys() == 3)
            {
                /*create the new nodes*/
                Node newRoot = new Node();
                newRoot.setLocation_in_file(newLocation());
                Node newRight = new Node();
                newRight.setLocation_in_file(newLocation());

                /*set the root*/
                newRoot.setLeaf(false);
                newRoot.addKeys(child.getArray_of_keys().remove(1));
                newRoot.incNumKeys();
                newRoot.setLocation_of_parent(-1);
                newRoot.addLocations(child.getLocation_in_file());
                newRoot.addLocations(newRight.getLocation_in_file());

                /*new right node of the parent
                * child node will be the left*/
                newRight.setLeaf(false);
                newRight.setLocation_of_parent(newRoot.getLocation_in_file());
                newRight.addKeys(child.getArray_of_keys().remove(1));
                newRight.incNumKeys();
                newRight.addLocations(child.getLocation_of_children().remove(2));
                newRight.addLocations(child.getLocation_of_children().remove(2));

                /*set the location of newRights children parent location to new Right*/
                Node temp = new Node();
                Node temp2 = new Node();
                temp = temp.read(newRight.getLocation_of_children().get(0));
                temp2 = temp2.read(newRight.getLocation_of_children().get(1));
                temp.setLocation_of_parent(newRight.getLocation_in_file());
                temp2.setLocation_of_parent(newRight.getLocation_in_file());


                /*fix my left child*/
               child.setNum_of_keys(child.getNum_of_keys() - 2);
               child.setLocation_of_parent(newRoot.getLocation_in_file());

               /*write*/
                temp.write();
                temp2.write();
                newRoot.write();
                newRight.write();
                child.write();

                root = newRoot;
            }
        }
        else
        {
            /*internal node split*/
        }



    }

    public void splitRoot() throws IOException {
        Node left = new Node();
        Node right = new Node();

        /*set leafs*/
        left.setLeaf(true);
        right.setLeaf(true);
        root.setLeaf(false);

        /*set parent of the nodes*/
        left.setLocation_of_parent(root.getLocation_in_file());
        right.setLocation_of_parent(root.getLocation_in_file());

        /*add the keys to the correct spots*/
        right.addKeys(root.getArray_of_keys().remove(2));
        right.incNumKeys();
        left.addKeys(root.getArray_of_keys().remove(0));
        left.incNumKeys();

        /*set root back to 1 number of keys*/
        root.setNum_of_keys(1);

        /*create new locations for the children*/
        left.setLocation_in_file(newLocation());
        right.setLocation_in_file(newLocation());

        /*add those locations to the root node
         * add left first */
        root.addLocations(left.getLocation_in_file());
        root.addLocations(right.getLocation_in_file());

        /*overrite root in the file
         * add left and right to the file*/
        root.write();
        left.write();
        right.write();

    }

    /*comparing the business name to a node and see which side it will go down too
    * if node has one key, 0 == left, 1 == right
    * if node has two keys, 0 == left, 1 == middle, 2 == right*/
    public int getSide(Node t, String business)
    {
        if(t.getNum_of_keys() == 1)
        {
            /*the string is less than or equal to the only key, means that we have to go to the left child*/
            if(business.compareTo(t.getArray_of_keys().get(0)) < 0 || business.compareTo(t.getArray_of_keys().get(0)) == 0)
            {
                return 0;   //left child
            }
            else
                return 1;   //right child
        }
        else    //if we have 2 keys in one node
        {
            /*the string is less than or equal to the only key, means that we have to go to the left child*/
            if(business.compareTo(t.getArray_of_keys().get(0)) < 0 || business.compareTo(t.getArray_of_keys().get(0)) == 0)
            {
                return 0;   //left child
            }
            else if(business.compareTo(t.getArray_of_keys().get(1)) > 0)
            {
                return 2;   //right child
            }
            else
                return 1; //middle child
        }

    }

    public void sortChild(Node t) throws IOException{
        ArrayList<Long> location_of_children = new ArrayList<Long>();
        int removeLoc = 0;
        while(t.getLocation_of_children().size() != 0)
        {
            Node min = new Node();

            min = min.read(t.getLocation_of_children().get(0));

            for(int i = 0; i < t.getLocation_of_children().size(); i++)
            {
                int next = i + 1;

                if(t.getLocation_of_children().size() != 1 && t.getLocation_of_children().size() != next)
                {
                    Node compare = new Node();
                    compare = compare.read(t.getLocation_of_children().get(next));

                    if(min.getArray_of_keys().get(0).compareTo(compare.getArray_of_keys().get(0)) > 0)
                    {
                        min = compare;
                        removeLoc = next;
                    }
                }
            }

            location_of_children.add(min.getLocation_in_file());
            if(t.getLocation_of_children().size() == 1)
            {
                t.getLocation_of_children().remove(0);
            }
            else
                t.getLocation_of_children().remove(removeLoc);

        }
        t.setLocation_of_children(location_of_children);
        t.write();
        System.out.println("***************** AFTER SORTING");
        t.print();
    }

    public void printRoot() throws IOException {

            System.out.println("**************ROOT*****************************");
            System.out.println("Num of keys: " + root.getNum_of_keys());
            System.out.println("leaf: " + root.isLeaf());
            System.out.println("Location in file: " + root.getLocation_in_file());
            System.out.println("Location of parent: " + root.getLocation_of_parent());
            root.getLocation_of_children().forEach(System.out::println);
            root.getArray_of_keys().forEach(System.out::println);
            System.out.println("*******************************************");

    }
}
