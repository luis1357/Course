package com.yeah.ruisu.webviews;

public class MyContact
{
    String Name;
    String Number;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public MyContact(String InName, String InNumber)
    {

        this.Name = InName;
        this.Number = InNumber;
    }
}
