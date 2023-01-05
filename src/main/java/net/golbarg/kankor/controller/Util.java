package net.golbarg.kankor.controller;

import javafx.scene.image.Image;
import net.golbarg.kankor.model.Gender;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Util {
    public static File projectLocationFolder = new File("");
    public static File userImageLocationFolder = new File(projectLocationFolder.getAbsolutePath(), "/assets/user/profile_image/");
    public static String defaultUserImage = "user_profile.png";


    public static Image convertFileToImage(File imageFile) throws FileNotFoundException {
        return new Image(new FileInputStream(imageFile));
    }

    public static boolean saveFile(File selectedFile, String path) throws IOException {
        if(selectedFile != null) {
            byte[] selected_file_bytes = Files.readAllBytes(selectedFile.toPath());
            File newFile = new File(path, selectedFile.getName());

            if(newFile.exists()) {
                newFile.delete();
            }

            Files.write(newFile.toPath(), selected_file_bytes);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Creates a string left padded to the specified width with the supplied
     * padding character.
     *
     * @param fieldWidth
     *            the length of the resultant padded string.
     * @param padChar
     *            a character to use for padding the string.
     * @param s
     *            the string to be padded.
     * @return the padded string.
     */
    public static String pad(int fieldWidth, char padChar, String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = s.length(); i < fieldWidth; i++) {
            sb.append(padChar);
        }
        sb.append(s);

        return sb.toString();
    }
    
}
