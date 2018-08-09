package com.yeah.ruisu.week2day3;

public class Animal
{
    String Category, Description, Sound;
    int Name, Picture;

    public Animal(String category, int name,
                  String description, int picture,
                  String sound)
    {
        Category = category;
        Name = name;
        Description = description;
        Picture = picture;
        Sound = sound;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getName() {
        return Name;
    }

    public void setName(int name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPicture() {
        return Picture;
    }

    public void setPicture(int picture) {
        Picture = picture;
    }

    public String getSound() {
        return Sound;
    }

    public void setSound(String sound) {
        Sound = sound;
    }
}
