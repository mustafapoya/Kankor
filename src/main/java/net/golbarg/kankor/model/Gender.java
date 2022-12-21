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

    public static Map<String, Gender> typeMapping = new HashMap<>();
    static {
        typeMapping.put(Male.value, Male);
        typeMapping.put(Female.value, Female);
        typeMapping.put(UNKNOWN.value, UNKNOWN);
    }
    public static Gender getGender(String gender) {
        if (typeMapping.get(gender) == null) {
            throw new RuntimeException(String.format("There is no gender mapping with name (%s)"));
        }
        return typeMapping.get(gender);
    }
}
