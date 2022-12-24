package net.golbarg.kankor;

import net.golbarg.kankor.controller.Util;

import java.io.File;
import java.util.Arrays;

public class TestCode {

    public static void main(String[] args) {
        File file = new File(Util.userImageLocationFolder.getAbsolutePath() + "/user_profile.png");
        System.out.println(Util.userImageLocationFolder.getAbsolutePath());
        System.out.println(file.exists());
        System.out.println(file.getName());
        String [] parts = file.getName().split("\\.");
        System.out.println(Arrays.toString(parts));
    }


}
