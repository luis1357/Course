package com.yeah.ruisu.restcalls;

import com.google.gson.annotations.SerializedName;

public class Person
{

    @SerializedName("name")
    private String Name;
    @SerializedName("nation")
    private String Nation;
    @SerializedName("age")
    private int Age;
    @SerializedName("weight")
    private int Weight;

    public Person(String name, String nation, int age, int weight)
    {
        Name = name;
        Nation = nation;
        Age = age;
        Weight = weight;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String getNation()
    {
        return Nation;
    }

    public void setNation(String nation)
    {
        Nation = nation;
    }

    public int getAge()
    {
        return Age;
    }

    public void setAge(int age)
    {
        Age = age;
    }

    public int getWeight()
    {
        return Weight;
    }

    public void setWeight(int weight)
    {
        Weight = weight;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "Name='" + Name + '\'' +
                ", Nation='" + Nation + '\'' +
                ", Age=" + Age +
                ", Weight=" + Weight +
                '}';
    }
}
