package com.yeah.ruisu.mvvmarchitecturecomponents;

public class Forecast
{
    //int ImageId;
    String maxTemp, minTemp, ImageUrl;

    public Forecast(String ImageUrl, String maxTemp, String minTemp) {
        this.ImageUrl = ImageUrl;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public String getImageId() {
        return ImageUrl;
    }

    public void setImageId(String imageUrl) {
        this.ImageUrl = imageUrl;
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
