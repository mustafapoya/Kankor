package net.golbarg.kankor.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JSONControllerTest {


    @Test
    public void testJsonNews() {
        try {
            System.out.println(JSONController.getNews());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}