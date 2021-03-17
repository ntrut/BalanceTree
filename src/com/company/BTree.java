package com.company;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BTree
{
    private Node root;
    private int height;

    static class Node
    {
        private int height;
        private int num_of_keys;
        private final ArrayList<String> Array_of_keys = new ArrayList<String>();        //Array of all keys
        private Node parent;                                                            //Node contains its parent so I know the parent
        private Node[] Array_of_nodes = new Node[3];                                    //Array of all of the nodes, children of Node
        private boolean leaf;                                                           //Is true if the node is a leaf. Otherwise it is false

        public Node()
        {

        }

        public Node(ByteBuffer buf)
        {

        }

        public Node(String business, int height)
        {
            this.height = height;
            Array_of_keys.add(business);
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getNum_of_keys() {
            return num_of_keys;
        }

        public void setNum_of_keys(int num_of_keys) {
            this.num_of_keys = num_of_keys;
        }

        public ArrayList<String> getArray_of_keys() {
            return Array_of_keys;
        }

        public Node[] getArray_of_nodes() {
            return Array_of_nodes;
        }

        public boolean isLeaf() {
            return leaf;
        }

        public void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }


        public void add_key(String str)
        {
            this.Array_of_keys.add(str);
            this.num_of_keys++;
            Collections.sort(this.Array_of_keys);
        }

        public void add_node(Node t)
        {
            for(int i = 0; i < this.Array_of_nodes.length; i++)
            {
                if(Array_of_nodes[i] == null)
                {
                    Array_of_nodes[i] = t;
                    break;
                }

            }
        }

        public int get_size_nodes()
        {
            int total = 0;
            while(Array_of_nodes[total] != null)
            {
                total++;
                if(total == 4)
                {
                    break;
                }
            }
            return total;
        }
    }

    public void createTree()
    {
        root = new Node();
        root.setLeaf(true);
        root.setNum_of_keys(0);
        root.setHeight(0);
    }

    public void insert(String business)
    {
        if(root.isLeaf())
        {
            root.add_key(business);
            if(root.getNum_of_keys() == 3)
            {
                splitChild(root);
                System.out.println("here");
            }
        }
        else if(!root.isLeaf())
        {
            Node temp = new Node();
            temp = root;

            while(!temp.isLeaf())
            {
                int side = getSide(temp, business);
                temp = temp.getArray_of_nodes()[side];
            }

            temp.add_key(business);
            //SPLIT CHILD HERE ***************************
        }

    }

    public void splitChild(Node t)
    {
        Node x = new Node();
        Node y = new Node();
        /*if root has 3 keys*/
        if(t.getHeight() == 0)
        {
            //System.out.println(t.getArray_of_keys().get(0));
            x.add_key(t.getArray_of_keys().remove(0)); //left node
            //System.out.println(t.getArray_of_keys().get(1));
            y.add_key(t.getArray_of_keys().remove(1)); //right node

            /*parent now has two children*/
            t.add_node(x);
            t.add_node(y);

            /*the new nodes have 1 key each*/
            t.setNum_of_keys(1);
            x.setNum_of_keys(1);
            y.setNum_of_keys(1);

            x.setParent(t);
            y.setParent(t);

            /*set the new 2 child nodes height + 1 from the parent*/
            x.setHeight(t.getHeight() + 1);
            y.setHeight(t.getHeight() + 1);
            /*the two new nodes dont have children*/
            if(x.get_size_nodes() == 0 && y.get_size_nodes() == 0)
            {
                x.setLeaf(true);
                y.setLeaf(true);
                t.setLeaf(false);
            }
        }
        /*if its a leaf node*/

        /*if its a internal node*/
    }

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

    public void Traverse()
    {
        for(int i= 0; i < root.getNum_of_keys(); i++)
        {

            System.out.println(root.getArray_of_keys().get(i));
        }

        for(int i = 0; i < root.getArray_of_nodes().length; i++)
        {

        }

        System.out.println(root.toString());
    }
}
