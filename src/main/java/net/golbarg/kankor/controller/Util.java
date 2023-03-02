package net.golbarg.kankor.controller;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import net.golbarg.kankor.model.Gender;
import net.golbarg.kankor.model.Position;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class Util {
    public static File projectLocationFolder = new File("");
    public static File userImageLocationFolder = new File(projectLocationFolder.getAbsolutePath(), "/assets/user/profile_image/");
    public static String defaultUserImage = "user_profile.png";
    public static String PACKAGE_PATH = "/net/golbarg/kankor/";

    public static String fileFromPcakge(String file) {
        return PACKAGE_PATH + file;
    }
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

    public static void sendEmail() throws URISyntaxException, IOException {
        Desktop desktop;
        if (Desktop.isDesktopSupported()
                && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
            URI mailto = new URI("mailto:contact@golbarg.net?subject=Kankor%20App");
            desktop.mail(mailto);
        } else {
            // TODO fallback to some Runtime.exec(..) voodoo?
            throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
        }
    }

    public static void setStageLocation(Stage stage) {
        Position position = new Position(Position.CORNER.BOTTOM_RIGHT, stage.getWidth(), stage.getHeight());
        stage.setX(position.getXLocation());
        stage.setY(position.getYLocation());
        System.out.println(Position.getTaskbarHeight2());
    }

    public static void displayAtCenter(Stage stage) {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    public static void displayAtCenterOf(Stage parent, Stage child) {
        double centerXPosition = parent.getX() + parent.getWidth()/2d;
        double centerYPosition = parent.getY() + parent.getHeight()/2d;

        child.setOnShown(event -> {
            child.setX(centerXPosition - child.getWidth()/2d);
            child.setY(centerYPosition - child.getHeight()/2d);
        });
    }

    public static boolean isServerAvailable() {
        try {
            URL url = new URL(SystemController.SERVER_ADDRESS);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            conn.getInputStream().close();
            return true;

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }

}
