
package com.yeah.ruisu.weekend3.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RetailPrice_ implements Serializable
{
    private static final long serialVersionUID = -29238982928391L;

    @Expose
    private Double amountInMicros;
    @Expose
    private String currencyCode;

    public Double getAmountInMicros() {
        return amountInMicros;
    }

    public void setAmountInMicros(Double amountInMicros) {
        this.amountInMicros = amountInMicros;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}
