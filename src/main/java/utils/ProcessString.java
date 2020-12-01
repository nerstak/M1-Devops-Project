package utils;

import java.sql.Date;

public class ProcessString {
    /**
     * Check if a set of string are empty or not
     * @param strings, an array of string to check
     * @return true if at least one string is empty, else return false
     */
    public static boolean areStringEmpty(String... strings){
        for (String str : strings) {
            if (str.isEmpty())
                return true;
        }
        return false;
    }

    /**
     * Capitalize the first letter of the string and lower cases all other letter
     * Ex : hELlO => Hello
     * @param str, the string to transform
     * @return string
     */
    public static String capitalizeAndLowerCase(String str){
        if(str.isEmpty()) {
            return str;
        }
        if(str.length() == 1) {
            return str.toUpperCase();
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * Date comparator (before)
     * @param date1, the first date to compare
     * @param date2, the second date to compare
     * @return true if date1 is before date2
     */
    public static boolean isDateBefore(String date1, String date2){
        return (Date.valueOf(date1)).before(Date.valueOf(date2));
    }

}
