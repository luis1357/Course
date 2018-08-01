import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

class Homework1 
{ 
	
	public static void main(String[] args) 
	{
		/* First just a blank space to make it look nice in the console. */ 
		System.out.println("\n");


		/* +-+-+ PROBLEM 1 +-+-+ */

		/* We create the list to hold the values. */
		List<String> MyList = new LinkedList<String>();

		/* We fill the list with repeated values. */
		for (int i = 0; i < 2; i++) 
		{
			MyList.add("Cat");
		}

		for (int i = 0; i < 3; i++) 
		{
			MyList.add("potato");
		}

		/* We fill the list with non-duplicate values. */
		MyList.add("Dog");
		MyList.add("Tomatoe");

		/* We print the info to the user in the console. */
		System.out.println("My List : " + MyList);
		System.out.println("Here are the duplicate elements from list : " + FindDuplicates(MyList));
		System.out.println("\n");

		/* +-+-+ PROBLEM 2 +-+-+ */

		IsPalindrome("tacocat");
		IsPalindrome("tigershark");

		/* +-+-+ PROBLEM 3 +-+-+ */
		FizzBuzz(9);
		FizzBuzz(10);
		FizzBuzz(15);
		FizzBuzz(22);

		/* +-+-+ PROBLEM 4 +-+-+ */
		isAnagram("taco", "coat");
		isAnagram("dog", "cat");

		/* +-+-+ PROBLEM 5 +-+-+ */
		System.out.println("\n");
		TimesTable(10, 10);
	}
	
	/* Problem 1, Check for duplicates. */
	public static Set<String> FindDuplicates(List<String> listContainingDuplicates) {
 
		final Set<String> setToReturn = new HashSet<String>();
		final Set<String> set1 = new HashSet<String>();
 
		for (String yourInt : listContainingDuplicates) {
			if (!set1.add(yourInt)) {
				setToReturn.add(yourInt);
			}
		}
		return setToReturn;
	}

	/* Problem 2, check if a string is palindrome. */
	public static void IsPalindrome(String InStr)
  	{
    	/* We reverse the string. */
    	String RvrsWrd = new StringBuffer(InStr).reverse().toString();
	 
	    /* Now we check if it is the same as the original. */
	    if (InStr.equals(RvrsWrd))
	    {
	      System.out.println("The word" + InStr + "is a palindrome");
	    }
			 
	    else
	    {
	      System.out.println("The word" + InStr + "is NOTa palindrome");
	    }
    }
	

	/* Problem 3, print fizz for modulus 3, buzz for modulus by 5 *
	 * 	fizz if it is by 3 and 5, and it repeats the integer for  *
	 * 	anything else. 											  */
	public static void FizzBuzz(Integer InInt)
  	{
    	if ((InInt % 3) == 0)
    	{
    		System.out.println("\nFIZZ");
    	}
    	else if ((InInt % 5) == 0)
    	{
    		System.out.println("\nBUZZ");	
    	}	
    	else if (((InInt % 5) == 0) && ((InInt % 3) == 0))
    	{
    		System.out.println("\nFIZZ");	
    	}
    	else
    	{
    		System.out.println("\n" + InInt);	
    	}
  	}

  	/* Problem 4, check if two strings are anagrams. */
  	public static void isAnagram(String firstWord, String secondWord) 
  	{
		char[] word1 = firstWord.replaceAll("[\\s]", "").toCharArray();
		char[] word2 = secondWord.replaceAll("[\\s]", "").toCharArray();

		Arrays.sort(word1);
		Arrays.sort(word2);

		boolean Result = Arrays.equals(word1, word2);

		if (Result)
		{
			System.out.println("\nThe word " + firstWord + 
								" is an anagram of " + secondWord);
		}
		else
		{
			System.out.println("\nThe word " + firstWord + 
								" is NOT an anagram of " + secondWord);	
		}
	}

	/* Problem 5, a multiplication table from 1 to 10. */
	public static void TimesTable(int InRow, int InColumn)
	{
	    int [][] RtrnTbl = new int[InRow][InColumn];

	    for (int row = 0; row < RtrnTbl.length ; row++)
	    {
	        for (int column = 0; column < RtrnTbl[row].length; column++)
	        {
	             RtrnTbl[row][column] = (row+1)*(column+1);         }

	    }
	    
	    for (int row = 0; row < RtrnTbl.length ; row++)
    	{
        	for (int column = 0; column < RtrnTbl[row].length; column++)
        	{
            	System.out.printf("%2d ",RtrnTbl[row][column]);
        	}
        	System.out.println();

    	}
	}
}