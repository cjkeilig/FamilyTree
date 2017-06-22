package com.cory.projects.familytree;

/**
 * Created by unouser on 6/22/2017.
 */

public class Tree {
    private int size;
    Node root;

    public Node getNodeAt(int id) {
        //code to get a Node
    }

    public void insertChildAt(Node child, int id) {
        Node node = getNodeAt(id);
        node.addChild(child);
    }

    public boolean insertSpouseAt(int id) {
        Node node = getNodeAt(id);
    }

    public boolean insertMomAt(int id) {

    }

    public boolean insertDadAt(int id) {

    }

}
