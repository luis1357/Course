package com.yeah.ruisu.weekend4;

public class MyGooglePlaces
{
    private String name, category, rating,
                    opennow, vicinity, iconUrl;
    private double latitude, longitude;

    public MyGooglePlaces ()
    {
        this.name = "";
        this.category = "";
        this.rating = "";
        this.opennow = "";
        this.vicinity = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getOpennow() {
        return opennow;
    }

    public void setOpennow(String opennow) {
        this.opennow = opennow;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public void setLatLng(double lat,double lon)
    {
        this.latitude=lat;
        this.longitude=lon;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
