package net.golbarg.kankor.model;

import java.util.ArrayList;

public class Gender {
    public static final Gender MALE    = new Gender("M", "مرد");
    public static final Gender FEMALE  = new Gender("F", "زن");
    public static final Gender UNKNOWN = new Gender("U", "نامشخص");
    private String key;
    private String label;

    public Gender(String key, String label) {
        this.key = key;
        this.label = label;
    }

    public String getKey() {
        return key;
    }

    public String getLabel() {
        return label;
    }

    public static ArrayList<Gender> getAll() {
        ArrayList<Gender> list =  new ArrayList<Gender>();
        list.add(MALE);
        list.add(FEMALE);
        list.add(UNKNOWN);
        return list;
    }

    public static Gender getGender(String key) {
        for(Gender gender: getAll()) {
            if (gender.key.equals(key))
                    return gender;
        }
        return Gender.UNKNOWN;
    }
}

