package com.example.demo.model;
import java.io.Serializable;
import java.util.Objects;

public class Patient implements Identifiable<Integer>, Serializable{
    public int ID;
    public String name;
    public int age;

    public Patient(int ID, String name, int age) {
        if(age < 0) try {
            throw new RuntimeException("the age must be positive");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        this.ID = ID;
        this.name = name;
        this.age = age;
    }

    public Patient() {
        this.ID = 0;
        this.name = "";
        this.age = 0;
    }

    @Override
    public String toString() {
        String s = "Patient with id: " + this.ID + " is named " + this.name + ", "+ this.age + " years old" + "\n";

        return s;
    }

    public int getId() {
        return ID;
    }

    public void setId(int Id) {
        this.ID = Id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int a) {
        if(age < 0) try {
            throw new RuntimeException("the age must be positive");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        this.age = a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return ID == patient.ID && age == patient.age && name.equals(patient.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, age);
    }

    @Override
    public Integer getID() {
        return this.ID;
    }

    @Override
    public void setID(Integer id) {
        this.ID = id;
    }
}
