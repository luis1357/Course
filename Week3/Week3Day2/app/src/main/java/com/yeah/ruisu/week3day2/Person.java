package com.yeah.ruisu.week3day2;

public class Person
{
    String Name, Age, Gender, Nationality;

    public Person(String name, String age, String gender, String nationality)
    {
        Name = name;
        Age = age;
        Gender = gender;
        Nationality = nationality;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String getAge()
    {
        return Age;
    }

    public void setAge(String age)
    {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender)
    {
        Gender = gender;
    }

    public String getNationality()
    {
        return Nationality;
    }

    public void setNationality(String nationality)
    {
        Nationality = nationality;
    }
}
