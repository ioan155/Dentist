package com.example.demo.model;

public interface Identifiable<Tid>{
    Tid getID();
    void setID(Tid id);
}