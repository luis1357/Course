package com.yeah.ruisu.mvvmexample;

public class Person
{
    private String firstName, lastName,
                    favoriteAnimal;
    private int age;

    public Person(String firstName, String lastName, String favoriteAnimal, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.favoriteAnimal = favoriteAnimal;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFavoriteAnimal() {
        return favoriteAnimal;
    }

    public void setFavoriteAnimal(String favoriteAnimal) {
        this.favoriteAnimal = favoriteAnimal;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", favoriteAnimal='" + favoriteAnimal + '\'' +
                ", age=" + age +
                '}';
    }
}
