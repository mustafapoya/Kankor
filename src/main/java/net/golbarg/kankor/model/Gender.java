package net.golbarg.kankor.model;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
    Male("M"),
    Female("F"),
    UNKNOWN("U");

    private String value;
    Gender(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Map<String, Gender> genderMap = new HashMap<>();
    static {
        genderMap.put(Male.value, Male);
        genderMap.put(Female.value, Female);
        genderMap.put(UNKNOWN.value, UNKNOWN);
    }
    public static Gender getGender(String gender) {
        if (genderMap.get(gender) == null) {
            throw new RuntimeException(String.format("There is no gender mapping with name (%s)"));
        }
        return genderMap.get(gender);
    }
}
