package com.yeah.ruisu.weekend3_1;

public class Sms
{
    private String id;
    private String address;
    private String msg;
    private String readState;
    private String time;
    private String folderName;

    /*
    public Sms(String id, String address,
               String msg, String readState,
               String time, String folderName)
    {
        this.id = id;
        this.address = address;
        this.msg = msg;
        this.readState = readState;
        this.time = time;
        this.folderName = folderName;
    }*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReadState() {
        return readState;
    }

    public void setReadState(String readState) {
        this.readState = readState;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}