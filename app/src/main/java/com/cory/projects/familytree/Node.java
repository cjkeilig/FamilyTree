package com.cory.projects.familytree;

import java.util.ArrayList;
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
        this.children = new ArrayList<Integer>();
    }

    private int spouse;
    private int dad;
    private int mom;
    private List<Integer> children;

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

    public int getSpouse() {
        return spouse;
    }

    public void setSpouse(int spouse) {
        this.spouse = spouse;
    }

    public int getDad() {
        return dad;
    }

    public void setDad(int dad) {
        this.dad = dad;
    }

    public int getMom() {
        return mom;
    }

    public void setMom(int mom) {
        this.mom = mom;
    }

    public List<Integer> getChildren() {
        return children;
    }

    public void removeChild(int child) {
        children.remove(child);
    }

    public void addChild(int child) {
        children.add(child);
    }
}
