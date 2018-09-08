package com.example.luisenriquez.week6test;

public class main
{
    public static void main(String[] args)
    {
        int[] numberArray = new int[] {1, 2, 3, 4, 5, 6, 7};

        /* Printing Original Array. */
        System.out.println("Original Array: ");
        printArray(numberArray);

        rotateBruteForce(numberArray, 3);

        rotateByMovetoaNewArray(numberArray, 3);

        rotateByReverse(numberArray, 3);
    }

    /* By Brute Force. */
    public static void rotateBruteForce (int[] nums, int k)
    {

        System.out.println("Ordering by BruteForce: ");

        int temp, previous;
        int[] tempArray;

        tempArray = nums;

        for(int i = 0; i < k; i++)
        {
            previous = tempArray[tempArray.length - 1];

            for (int j = 0; j < tempArray.length; j++)
            {
                temp = tempArray[j];
                tempArray[j] = previous;

                previous = temp;

            }
        }

        printArray(tempArray);
    }

    /* Moving the Array in a new One. */
    public static void rotateByMovetoaNewArray (int[] nums, int k)
    {
        int[] tempArray;

        tempArray = nums;

        System.out.println("Moving the Array to a new one: ");

        int[] a = new int[tempArray.length];

        for(int i = 0; i < tempArray.length; i++)
        {
            a[((i + k) % tempArray.length)] = tempArray[i];
        }

        for (int i = 0; i < tempArray.length; i++)
        {
            tempArray[i] = a[i];
        }

        printArray(tempArray);
    }

    /* Using Reverse. */
    public static void rotateByReverse (int[] nums, int k)
    {
        int[] tempArray;
        tempArray = nums;

        System.out.println("Using Reverse: ");

        k %= tempArray.length;

        reverse(tempArray, 0, tempArray.length -1);
        reverse(tempArray, 0, k -1);
        reverse(tempArray, k, tempArray.length -1);

        printArray(tempArray);
    }

    public static void reverse(int[] nums, int start, int end)
    {
        while (start < end)
        {
            int temp = nums[start];

            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void printArray(int[] inNums)
    {
        System.out.print("The Array is: [");

        for (int i = 0; i < inNums.length; i ++)
        {
            if (i == (inNums.length - 1))
            {
                System.out.println(inNums[i] + "]");
                System.out.println("");
            }
            else
            {
                System.out.print(inNums[i] + ", ");
            }
        }
    }

}
