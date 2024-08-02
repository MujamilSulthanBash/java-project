package com.util;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validator {

    public static boolean stringValidate(String name) {
        String regex = "^[a-zA-Z]+([a-zA-Z]+)*$";
        if (Pattern.matches(regex, name)) {
            return false;
        } else {
            return true;
        }
    }
}