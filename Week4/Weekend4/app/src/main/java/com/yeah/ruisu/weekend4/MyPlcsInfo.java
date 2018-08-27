package com.yeah.ruisu.weekend4;

public class MyPlcsInfo
{
    String plcName, plcAddress, plcImgUrl;

    public MyPlcsInfo(String plcName, String plcAddress, String plcImgUrl)
    {
        this.plcName = plcName;
        this.plcAddress = plcAddress;
        this.plcImgUrl = plcImgUrl;
    }

    public String getPlcName() {
        return plcName;
    }

    public void setPlcName(String plcName) {
        this.plcName = plcName;
    }

    public String getPlcAddress() {
        return plcAddress;
    }

    public void setPlcAddress(String plcAddress) {
        this.plcAddress = plcAddress;
    }

    public String getPlcImgUrl() {
        return plcImgUrl;
    }

    public void setPlcImgUrl(String plcImgUrl) {
        this.plcImgUrl = plcImgUrl;
    }
}
