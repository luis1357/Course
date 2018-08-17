package com.yeah.ruisu.week3daily1;

public class MyObject
{
    String LuckyNmbr, RandomFood, RandomFact;
    int ImageName;

    public MyObject(int imageName, String luckyNmbr, String randomFood, String randomFact)
    {
        ImageName = imageName;
        LuckyNmbr = luckyNmbr;
        RandomFood = randomFood;
        RandomFact = randomFact;
    }

    public int getImageName()
    {
        return ImageName;
    }

    public void setImageName(int imageName)
    {
        ImageName = imageName;
    }

    public String getLuckyNmbr()
    {
        return LuckyNmbr;
    }

    public void setLuckyNmbr(String luckyNmbr)
    {
        LuckyNmbr = luckyNmbr;
    }

    public String getRandomFood()
    {
        return RandomFood;
    }

    public void setRandomFood(String randomFood)
    {
        RandomFood = randomFood;
    }

    public String getRandomFact()
    {
        return RandomFact;
    }

    public void setRandomFact(String randomFact)
    {
        RandomFact = randomFact;
    }
}
