package net.golbarg.kankor.util;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

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
}
