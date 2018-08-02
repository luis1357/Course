package com.example.admin.layout_and_views;

import java.io.Serializable;

public class Person implements Serializable{

    String name;
    String gender;

    public Person(String name, String gender)
    {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
