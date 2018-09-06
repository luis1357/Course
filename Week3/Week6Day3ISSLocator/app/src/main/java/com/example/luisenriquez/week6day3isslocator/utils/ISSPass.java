package com.example.luisenriquez.week6day3isslocator.utils;

public class ISSPass
{
    String duration, riseTime;

    public ISSPass(String duration, String riseTime)
    {
        this.duration = duration;
        this.riseTime = riseTime;
    }

    public String getDuration()
    {
        return duration;
    }

    public void setDuration(String duration)
    {
        this.duration = duration;
    }

    public String getRiseTime()
    {
        return riseTime;
    }

    public void setRiseTime(String riseTime)
    {
        this.riseTime = riseTime;
    }
}
