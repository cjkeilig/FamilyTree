package com.cory.projects.familytree;

import java.util.List;

/**
 * Created by unouser on 6/22/2017.
 */

public class Tree {
    List<Node> nodes;

    public Node getNodeAt(int id) {
        return nodes.get(id);
    }

    public void removeNodeAt(int id) {
        nodes.remove(id);
    }

    public int getIndexByNode(Node node) {
        return nodes.indexOf(node);
    }

    public void insertChildAt(Node child, int id) {
        nodes.add(child);
        Node node = getNodeAt(id);
        node.addChild(nodes.indexOf(child));
    }

    public void insertSpouseAt(Node spouse, int id) {
        nodes.add(spouse);
        Node node = getNodeAt(id);
        node.setSpouse(nodes.indexOf(spouse));
    }

    public void insertMomAt(Node mom, int id) {
        nodes.add(mom);
        Node node = getNodeAt(id);
        node.setMom(nodes.indexOf(mom));
    }

    public void insertDadAt(Node dad, int id) {
        nodes.add(dad);
        Node node = getNodeAt(id);
        node.setDad(nodes.indexOf(dad));
    }

    public void updateNode(Node node, int id) {
        Node oldNode = getNodeAt(id);
        oldNode = node;
    }

}
