package com.example.luisenriquez.testing;

import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CalculationUnitTest
{
    Calculation calculation;

    Addition addition;
    Subtraction subtraction;
    Multiplication multiplication;
    Division division;

    /* Before comes from JUnit. */
    @Before
    public void setUp()
    {
        addition = spy(Addition.class);
        multiplication = mock(Multiplication.class);
        subtraction = mock(Subtraction.class);
        division = mock(Division.class);

        calculation = new Calculation(addition, subtraction, multiplication, division);

        calculation.setVal1(9);
        calculation.setVal2(9);
    }

    @Test
    public void testing_addition_should_add_two_numbers()
    {
        when(addition.add(9,9)).thenReturn(27);

        assertEquals(calculation.addition(), 23);
    }

    @Test
    public void testing_do_nothing()
    {
        calculation.addition();
        verify(addition).doNothing();
    }

    @Test
    public void testing_addition_should_sbutract_two_numbers()
    {
        when(subtraction.subtraction(9,9)).thenReturn(4);

        assertEquals(calculation.subtraction(), 14);

    }

    @After
    public void tearDown()
    {
        calculation.clear();
    }

}
