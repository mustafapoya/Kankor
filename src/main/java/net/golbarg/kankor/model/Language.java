package net.golbarg.kankor.model;

import java.util.ArrayList;

public class Language {
    public static final Language DARI    = new Language("DARI", "دری");
    public static final Language PASHTO  = new Language("PASHTO", "پشتو");
    public static final Language UZBIKI = new Language("UZBIKI", "انگلیسی");

    private String key;
    private String label;

    public Language(String key, String label) {
        this.key = key;
        this.label = label;
    }

    public String getKey() {
        return key;
    }

    public String getLabel() {
        return label;
    }

    public static ArrayList<Language> getAll() {
        ArrayList<Language> list =  new ArrayList<Language>();
        list.add(DARI);
        list.add(PASHTO);
        list.add(UZBIKI);
        return list;
    }

    public static Language getLanguage(String key) {
        for(Language language: getAll()) {
            if (language.key.equals(key))
                return language;
        }
        return Language.DARI;
    }

}
