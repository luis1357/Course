package com.yeah.ruisu.week3day4;

public class Forecast
{
    int ImageId;
    String maxTemp, minTemp;

    public Forecast(int imageId, String maxTemp, String minTemp) {
        this.ImageId = imageId;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        this.ImageId = imageId;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }
}
