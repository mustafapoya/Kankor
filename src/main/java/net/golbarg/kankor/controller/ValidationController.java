package net.golbarg.kankor.controller;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationController {
    public static final String PHONE_NUMBER_PATTERN = "^[+]*[0-9]{10,16}$";
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  +
                                                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String SPECIAL_CHAR_PATTERN = "[/*!@#$%^&*()\"|\\?/<>,.]";

    public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
        return pattern.matcher(phoneNumber).matches();
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();
    }

    public static boolean isInputJustText(String value) {
        if(value.trim().equals("")) {
            return false;
        } else {
            for (int i = 0; i < value.length(); i++) {
                if(!(Character.isLetter(value.charAt(i)) || value.charAt(i) == ' ')) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean isInputJustNumber(String value) {
        if(value.trim().equals("")) {
            return false;
        } else {
            for (int i = 0; i < value.length(); i++) {
                if(!(Character.isDigit(value.charAt(i)) || value.charAt(i) == ' ')) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean isInputTextOrNumber(String value) {
        if(value.trim().equals("")) {
            return false;
        } else {
            for (int i = 0; i < value.length(); i++) {
                if(!(Character.isLetter(value.charAt(i)) || Character.isDigit(value.charAt(i)) || value.charAt(i) == ' ')) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean isValidPassword(String p1, String p2) {
        return p1.trim().equals(p2.trim());
    }

    public static int getPasswordComplexity(String password) {
        int complexity = 0;

        if (isContainCharacter(password)) {
            complexity++;
        }
        if (isContainNumber(password)) {
            complexity++;
        }
        if (isContainSymbols(password)) {
            complexity++;
        }

        return complexity;
    }

    public static boolean isContainNumber(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isContainCharacter(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isContainSymbols(String txt) {
        Pattern regex = Pattern.compile(SPECIAL_CHAR_PATTERN);
        Matcher matcher = regex.matcher(txt);

        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

}
