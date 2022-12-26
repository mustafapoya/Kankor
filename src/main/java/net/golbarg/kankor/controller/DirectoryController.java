package net.golbarg.kankor.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DirectoryController {
    // Directory name entered by the user.
    private String directoryPath;
    // File object referring to the directory.
    private File directory;
    // Array of file names in the directory.
    private ArrayList<String> files;

    public DirectoryController(String directoryPath) {
        this.directoryPath = directoryPath;
        files = new ArrayList<>();
    }

    public ArrayList<String> getFiles() throws IOException {
        directory = new File(directoryPath);

        if(directory.isDirectory()) {
            String[] temp = directory.list();
            for (String file : temp) {
                files.add(file);
            }
        } else {
            if(!directory.exists()) {
                throw new IOException("There is no such a directory!");
            } else {
                throw new IOException("The path is not a directory!");
            }
        }
        return files;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

}
