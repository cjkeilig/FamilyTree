package com.cory.projects.familytree;

import java.util.List;

/**
 * Created by unouser on 6/22/2017.
 */

public class Node {
    private int id;
    private String name;
    private String birthdate;
    private String deathdate;
    private String location;

    public Node(int id, String name, String birthdate, String deathdate, String location) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.deathdate = deathdate;
        this.location = location;
    }

    private Node spouse;
    private Node dad;
    private Node mom;
    private List<Node> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getDeathdate() {
        return deathdate;
    }

    public void setDeathdate(String deathdate) {
        this.deathdate = deathdate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Node getSpouse() {
        return spouse;
    }

    public void setSpouse(Node spouse) {
        this.spouse = spouse;
    }

    public Node getDad() {
        return dad;
    }

    public void setDad(Node dad) {
        this.dad = dad;
    }

    public Node getMom() {
        return mom;
    }

    public void setMom(Node mom) {
        this.mom = mom;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void removeChild(Node child) {
        // code to remove child
    }

    public void addChild(Node child) {
        children.add(child);
    }
}
