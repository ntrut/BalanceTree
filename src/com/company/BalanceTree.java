package com.company;

import java.io.IOException;

public class BalanceTree
{
    private Node root;


    public void createTree(String businessName) throws IOException {
        root = new Node();
        root.setLeaf(true);
        root.addKeys(businessName);
        root.setLocation_in_file(0);
        root.write();
        root.read(0);
    }
}
