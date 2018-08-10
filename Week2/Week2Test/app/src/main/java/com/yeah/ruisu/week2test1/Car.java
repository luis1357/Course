package com.yeah.ruisu.week2test1;

public class Car
{
    String CarName, CarType, CarYear;

    public Car(String carName, String carType, String carYear) {
        CarName = carName;
        CarType = carType;
        CarYear = carYear;
    }

    public String getCarName() {
        return CarName;
    }

    public void setCarName(String carName) {
        CarName = carName;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String carType) {
        CarType = carType;
    }

    public String getCarYear() {
        return CarYear;
    }

    public void setCarYear(String carYear) {
        CarYear = carYear;
    }
}
