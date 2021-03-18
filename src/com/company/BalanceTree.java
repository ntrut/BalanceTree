package com.company;

import java.io.IOException;
import java.util.ArrayList;

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
            if(temp.getNum_of_keys() == 3)
            {
                //splitChild(temp);
            }
            temp.print();
        }

    }

    public void splitChild(Node child) throws IOException {
        /*split leaf node*/
        if(child.isLeaf())
        {
            Node temp = child.read(child.getLocation_of_parent());
            if(temp.getNum_of_keys() == 1)
            {
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
                newNode.setLeaf(false);

                /*set parent of new node*/
                newNode.setLocation_of_parent(temp.getLocation_of_parent());

                /*add new node to the parent children*/
                temp.addLocations(newNode.getLocation_in_file());
                sortChildren(temp);

                newNode.write();
            }
            else
            {
                /*if there are 2 keys in the parent node*/
            }

        }
        else if(child.getLocation_of_parent() == -1)    //has no parent, which means its the root
        {

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

    public void sortChildren(Node t) throws IOException {
        ArrayList<Long> location_of_children = new ArrayList<Long>();
        t.print();
        for(int i = 0; i < t.getLocation_of_children().size(); i++)
        {
                Node min = new Node();
                min = min.read(t.getLocation_of_children().get(i));
                int count = i + 1;
                int removeLoc = 0;
                while(count < t.getLocation_of_children().size())
                {
                    Node compare = new Node();
                    compare = compare.read(t.getLocation_of_children().get(count));

                    if(min.getArray_of_keys().get(0).compareTo(compare.getArray_of_keys().get(0)) > 0)
                    {
                        min = compare;
                        removeLoc = count;
                    }

                    count++;
                }
                System.out.println(min.getArray_of_keys().get(0));
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
        t.print();
    }
}
