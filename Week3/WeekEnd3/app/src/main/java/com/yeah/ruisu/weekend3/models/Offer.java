
package com.yeah.ruisu.weekend3.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Offer implements Serializable
{
    private static final long serialVersionUID = -29238982928391L;

    @Expose
    private Integer finskyOfferType;
    @Expose
    private ListPrice_ listPrice;
    @Expose
    private RetailPrice_ retailPrice;
    @Expose
    private Boolean giftable;

    public Integer getFinskyOfferType() {
        return finskyOfferType;
    }

    public void setFinskyOfferType(Integer finskyOfferType) {
        this.finskyOfferType = finskyOfferType;
    }

    public ListPrice_ getListPrice() {
        return listPrice;
    }

    public void setListPrice(ListPrice_ listPrice) {
        this.listPrice = listPrice;
    }

    public RetailPrice_ getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(RetailPrice_ retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Boolean getGiftable() {
        return giftable;
    }

    public void setGiftable(Boolean giftable) {
        this.giftable = giftable;
    }

}
