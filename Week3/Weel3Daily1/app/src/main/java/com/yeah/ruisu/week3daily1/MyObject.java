package com.yeah.ruisu.week3daily1;

public class MyObject
{
    String ImageName, LuckyNmbr, RandomFood, RandomFact;

    public MyObject(String imageName, String luckyNmbr, String randomFood, String randomFact)
    {
        ImageName = imageName;
        LuckyNmbr = luckyNmbr;
        RandomFood = randomFood;
        RandomFact = randomFact;
    }

    public String getImageName()
    {
        return ImageName;
    }

    public void setImageName(String imageName)
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
