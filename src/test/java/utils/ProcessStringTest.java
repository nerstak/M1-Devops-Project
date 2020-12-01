package utils;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.*;

public class ProcessStringTest {

    @Test
    public void areStringEmptyTest() {
        //Given
        String emptyString = "";
        String nonEmptyString = "something";

        //When
        boolean emptyStringAtBeginning = ProcessString.areStringEmpty(emptyString, nonEmptyString, nonEmptyString);
        boolean emptyStringInTheMiddle = ProcessString.areStringEmpty(nonEmptyString, emptyString, nonEmptyString);
        boolean emptyStringAtTheEnd = ProcessString.areStringEmpty(nonEmptyString, nonEmptyString, emptyString);
        boolean nonEmptyStrings = ProcessString.areStringEmpty(nonEmptyString, nonEmptyString, nonEmptyString);

        //Then
        assertThat(emptyStringAtBeginning, is(true));
        assertThat(emptyStringInTheMiddle, is(true));
        assertThat(emptyStringAtTheEnd, is(true));
        assertThat(nonEmptyStrings, is(false));
    }

    @Test(expected=NullPointerException.class)
    public void areStringEmptyNullTest() {
        //Given
        String nullString = null;
        String emptyString = "";
        String nonEmptyString = "something";

        //When
        ProcessString.areStringEmpty(nullString, nonEmptyString, emptyString);

        //Then
        //It will throw a NullPointerException
    }

    @Test
    public void capitalizeAndLowerCaseTest() {
        //Given
        String emptyString = "";
        String lowercaseString = "lowercase";
        String uppercaseString = "UPPERCASE";
        String oneLetterString = "l";

        //When
        String emptyStringResult = ProcessString.capitalizeAndLowerCase(emptyString);
        String lowercaseStringResult = ProcessString.capitalizeAndLowerCase(lowercaseString);
        String uppercaseStringResult = ProcessString.capitalizeAndLowerCase(uppercaseString);
        String oneLetterStringResult = ProcessString.capitalizeAndLowerCase(oneLetterString);

        //Then
        assertThat(emptyStringResult, is(""));
        assertThat(lowercaseStringResult, is("Lowercase"));
        assertThat(uppercaseStringResult, is("Uppercase"));
        assertThat(oneLetterStringResult, is("L"));
    }

    @Test(expected=NullPointerException.class)
    public void capitalizeAndLowerCaseNullTest() {
        //Given
        String nullString = null;

        //When
        ProcessString.capitalizeAndLowerCase(nullString);

        //Then
        //It will throw a NullPointerException
    }

    @Test
    public void isDateBeforeTest() {
        //Given
        String firstGoodFormatDate = "2020-10-10";
        String secondGoodFormatDate = "2020-11-11";

        //When
        boolean sameDates = ProcessString.isDateBefore(firstGoodFormatDate, firstGoodFormatDate);
        boolean firstThenSecondDate = ProcessString.isDateBefore(firstGoodFormatDate, secondGoodFormatDate);
        boolean secondThenFirstDate = ProcessString.isDateBefore(secondGoodFormatDate, firstGoodFormatDate);

        //Then
        assertThat(sameDates, is(false));
        assertThat(firstThenSecondDate, is(true));
        assertThat(secondThenFirstDate, is(false));
    }

    @Test(expected=IllegalArgumentException.class)
    public void isDateBeforeNullTest() {
        //Given
        String nullDate = null;
        String goodFormatDate = "2020-10-10";

        //When
        ProcessString.isDateBefore(nullDate, goodFormatDate);

        //Then
        //It will throw an IllegalArgumentException
    }

    @Test(expected=IllegalArgumentException.class)
    public void isDateBeforeEmptyTest() {
        //Given
        String emptyDate = "";
        String goodFormatDate = "2020-10-10";

        //When
        ProcessString.isDateBefore(emptyDate, goodFormatDate);

        //Then
        //It will throw an IllegalArgumentException
    }
    @Test(expected=IllegalArgumentException.class)
    public void isDateBeforeWrongFormatTest() {
        //Given
        String wrongFormatDate = "Wrong Format";
        String goodFormatDate = "2020-10-10";

        //When
        ProcessString.isDateBefore(wrongFormatDate, goodFormatDate);

        //Then
        //It will throw an IllegalArgumentException
    }
}