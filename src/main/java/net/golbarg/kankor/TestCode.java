package net.golbarg.kankor;

import net.golbarg.kankor.controller.DirectoryController;
import net.golbarg.kankor.controller.QuestionGenerator;
import net.golbarg.kankor.controller.Util;
import net.golbarg.kankor.db.TableQuestion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestCode {

    public static void main(String[] args) {
//        File file = new File(Util.userImageLocationFolder.getAbsolutePath() + "/user_profile.png");
//        System.out.println(Util.userImageLocationFolder.getAbsolutePath());
//        System.out.println(file.exists());
//        System.out.println(file.getName());
//        String [] parts = file.getName().split("\\.");
//        System.out.println(Arrays.toString(parts));
//
//        try {
//            ArrayList<String> files = new DirectoryController("assets/db").getFiles();
//            System.out.println(files);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }

        TableQuestion tableQuestion = new TableQuestion();
        tableQuestion.getQuestionsOf(QuestionGenerator.MATHEMATICS, 30);
    }


}
