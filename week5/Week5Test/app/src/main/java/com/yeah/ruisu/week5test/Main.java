package com.yeah.ruisu.week5test;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Switch;

import java.util.Arrays;
import java.util.Stack;

public class Main
{

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args)
    {
        System.out.println(CheckParenthesis("()[{}]"));
        System.out.println(CheckParenthesis("[(])"));

        System.out.println(CheckSubCopies("catcowcat", "cat", 2));
        System.out.println(CheckSubCopies("catcowcat", "cow", 2));
        System.out.println(CheckSubCopies("catcowcat", "cow", 1));
    }

    private static boolean CheckParenthesis(String str)
    {
        Stack<Character> myStack =  new Stack<Character>();

        char c;

        for (int i = 0; i < str.length(); i++)
        {
            c = str.charAt(i);

            switch (c)
            {
                case '(':
                    myStack.push(c);
                    break;

                case '{':
                    myStack.push(c);
                    break;

                case '[':
                    myStack.push(c);
                    break;

                case ')':

                    if (myStack.empty())
                    {
                        return false;
                    }
                    else if (myStack.peek() == '(')
                    {
                        myStack.pop();
                    }
                    else
                    {
                        return false;
                    }
                    break;

                case '}':

                    if (myStack.empty())
                    {
                        return false;
                    }
                    else if (myStack.peek() == '{')
                    {
                        myStack.pop();
                    }
                    else
                    {
                        return false;
                    }

                    break;

                case ']':

                    if (myStack.empty())
                    {
                        return false;
                    }
                    else if (myStack.peek() == '[')
                    {
                        myStack.pop();
                    }
                    else
                    {
                        return false;
                    }

                    break;

                default:
                    return false;
            }
        }

        return myStack.empty();
    }

    private static boolean CheckSubCopies(String inString, String inSubString, int nmbrRptns)
    {
        if (RcrsvFnc (inString, inSubString) == nmbrRptns)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private static int RcrsvFnc (String inString, String inSubString)
    {
        int strLngth = inString.length();
        int sbsLngth = inSubString.length();

        if (strLngth < sbsLngth)
        {
            return 0;
        }
        else if (inString.substring(0, sbsLngth).equals(inSubString))
        {
            return 1 + RcrsvFnc(inString.substring(1), inSubString);
        }
        else
        {
            return RcrsvFnc(inString.substring(1), inSubString);
        }
    }
}
