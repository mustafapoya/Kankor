package net.golbarg.kankor;

import java.util.Arrays;

public class MyCode {
    static long countKey(String[] ideas, String searchKey) {
        return Arrays.stream(ideas).filter(i -> i.equals(searchKey)).count();
    }
}
