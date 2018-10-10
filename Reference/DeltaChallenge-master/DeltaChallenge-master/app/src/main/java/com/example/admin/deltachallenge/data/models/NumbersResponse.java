
package com.example.admin.deltachallenge.data.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NumbersResponse {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("data")
    @Expose
    private List<Integer> data = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
