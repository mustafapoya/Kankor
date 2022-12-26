package net.golbarg.kankor.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationControllerTest {

    @Test
    void isValidPhoneNumber() {
        String phoneNumber = "+93799990990";
        assertEquals(true, ValidationController.isValidPhoneNumber(phoneNumber));
        phoneNumber = "0799990990";
        assertEquals(true, ValidationController.isValidPhoneNumber(phoneNumber));
        phoneNumber = "799990990";
        assertEquals(false, ValidationController.isValidPhoneNumber(phoneNumber));
    }

    @Test
    void isValidEmail() {
        String email = "ali@gmail.com";
        assertTrue(ValidationController.isValidEmail(email));
        email = "ali.gmail.com";
        assertFalse(ValidationController.isValidEmail(email));
    }

    @Test
    void isInputJustText() {
        String value = "ali";
        assertTrue(ValidationController.isInputJustText(value));
        value = "ali123";
        assertFalse(ValidationController.isInputJustText(value));
    }

    @Test
    void isInputJustNumber() {
        String value = "123";
        assertTrue(ValidationController.isInputJustNumber(value));
        value = "ali123";
        assertFalse(ValidationController.isInputJustNumber(value));
    }

    @Test
    void isInputTextOrNumber() {
        String value = "123";
        assertTrue(ValidationController.isInputJustNumber(value));
        value = "ali123";
        assertTrue(ValidationController.isInputJustNumber(value));
    }

    @Test
    void isValidPassword() {
        String value1 = "ali123";
        String value2 = "ali123";

        assertTrue(ValidationController.isValidPassword(value1, value2));
        value2 = "ali1234";
        assertFalse(ValidationController.isValidPassword(value1, value2));
    }

    @Test
    void getPasswordComplexity() {

    }

    @Test
    void isContainNumber() {

    }

    @Test
    void isContainCharacter() {

    }

    @Test
    void isContainSymbols() {

    }
}