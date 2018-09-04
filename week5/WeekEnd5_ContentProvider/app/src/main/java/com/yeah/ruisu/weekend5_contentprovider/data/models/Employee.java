package com.yeah.ruisu.weekend5_contentprovider.data.models;

public class Employee
{
    int id, age;
    String name, email, phone, address;

    public Employee setId(int id)
    {
        this.id = id;
        return this;
    }

    public Employee setAge(int age)
    {
        this.age = age;
        return this;
    }

    public Employee setName(String name)
    {
        this.name = name;
        return this;
    }

    public Employee setEmail(String email)
    {
        this.email = email;
        return this;
    }

    public Employee setPhone(String phone)
    {
        this.phone = phone;
        return this;
    }

    public Employee setAddress(String address)
    {
        this.address = address;
        return this;
    }

    public int getId()
    {
        return id;
    }

    public int getAge()
    {
        return age;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getAddress()
    {
        return address;
    }
}
