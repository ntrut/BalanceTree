package com.company;

import java.io.IOException;

public class BalanceTree
{
    private Node root;
    private long locations = 0;

    public void createTree(String businessName) throws IOException {
        root = new Node();
        root.setLeaf(true);
        root.addKeys(businessName);
        root.incNumKeys();
        root.setLocation_in_file(locations);
        root.write();
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
            /*call a split here*/
            temp.write();
            temp.read(temp.getLocation_in_file());
        }

    }



    public void splitChild(Node parent, Node child)
    {
        /*split leaf node*/

    }

    public void splitRoot() throws IOException {
        Node left = new Node();
        Node right = new Node();

        /*set leafs*/
        left.setLeaf(true);
        right.setLeaf(true);
        root.setLeaf(false);

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
}
