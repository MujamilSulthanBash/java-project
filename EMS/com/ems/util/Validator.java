package com.ems.util;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validator {
    /**
     * This method handle the String validation
     *
     * @param name 
     *     -  accept the string value
     * @return bolean 
     *     - given string is match the condition return true else false
     */
    public static boolean stringValidate(String name) {
        String regex = "^[a-zA-Z]+([ ][a-zA-Z]+)*$";
        if (Pattern.matches(regex, name)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean dobValidate(String dob) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dob, dateFormatter);
        return true;
    }
}