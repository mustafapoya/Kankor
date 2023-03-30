package net.golbarg.kankor.controller.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;

public class QuestionTextController extends TextFlow {
    public static final String QuestionSplitter = "#&#";
    public static File questionImageFolder = new File("assets/question/");
    public static final String questionImagePath = "file:///" + questionImageFolder.getAbsolutePath() + "/";

    private Text questionNumber;
    private String questionText;
    private int number = 0;

    String[] questionParts;
    ObservableList<Node> questionNodes = FXCollections.observableArrayList();

    public QuestionTextController(String questionText, int number) {
        this.questionText = questionText;
        this.number = number;
        convertQuestion();
    }

    private void convertQuestion() {
        if(number != -1) {
            questionNumber = new Text("سوال (" + number + ")  ");
            getChildren().add(questionNumber);
        } else {
            questionNumber = new Text("");
            getChildren().add(questionNumber);
        }

        // split question text parts
        questionParts = questionText.split(QuestionSplitter);

        // parsing question text
        for(int i = 0; i < questionParts.length; i++) {
            if(isImage(questionParts[i])) {
                try {
                    Image tmpImage = new Image(questionImagePath + getImageName(questionParts[i]));
                    questionNodes.add(new ImageView(tmpImage));

                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                    System.err.println("Image not found!");
                    Text text = new Text("Image not Found!");
                    text.setStyle("-fx-fill:red");
                    questionNodes.add(text);
                }
            } else {
                Text lbl = new Text(removeBackSlashSymbol(questionParts[i]));
                questionNodes.add(lbl);
            }
        }

        // adding to textFlow
        for(int i = 0; i < questionNodes.size(); i++) {
            getChildren().add(questionNodes.get(i));
        }

        // center text
        centerText();
        if(number != -1) {
            centerText(questionNumber);
        }
    }

    private boolean isImage(String text) {
        if(startWithFig(text)){
            return true;
        }else if (text.trim().endsWith(".jpg") || text.trim().endsWith(".png") || text.trim().endsWith(".gif")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean startWithFig(String text){
        if(text.toLowerCase().trim().startsWith("fig")){
            return true;
        }else{
            return false;
        }
    }

    private String getImageName(String text){
        String temp = text.trim();
        if(text.trim().startsWith("fig") && text.trim().endsWith(".png")){
            return temp;
        }else if(text.trim().startsWith("fig")){
            temp += ".png";
            return temp;
        }
        else{
            return temp;
        }
    }

    public ObservableList<ImageView> getImages() {
        ObservableList<ImageView> imgs = FXCollections.observableArrayList();
        for (int i = 0; i < questionNodes.size(); i++) {
            if (questionNodes.get(i) instanceof ImageView) {
                imgs.add((ImageView) questionNodes.get(i));
            }
        }
        return imgs;
    }

    public ObservableList<Text> getLabels() {
        ObservableList<Text> labels = FXCollections.observableArrayList();
        for (int i = 0; i < questionNodes.size(); i++) {
            if (questionNodes.get(i) instanceof Text) {
                labels.add((Text) questionNodes.get(i));
            }
        }
        return labels;
    }

    public void centerText() {
        if (getImages().size() > 0) {
            ObservableList<Text> labels = getLabels();
            Image img = getImages().get(0).getImage();
            double height = img.getHeight();

            for (int i = 0; i < labels.size(); i++) {
                labels.get(i).setTranslateY(-height / 2);
            }
        }
    }

    public void centerText(Text text){
        if(getImages().size() > 0){
            Image img = getImages().get(0).getImage();
            double height = img.getHeight();
            text.setTranslateY(-height/2);
        }
    }

    public static String removeBackSlashSymbol(String text){
        String temp;
        if(text.contains("\\")){
            temp = text.replace('\\', ' ');
        }else{
            temp = text;
        }
        return temp;
    }

    public void wrapText(){
        for (int i = 0; i < getLabels().size(); i++) {
            if(getLabels().get(i).getText().length() > 100){
                String temp = getLabels().get(i).getText().substring(0, getLabels().get(i).getText().indexOf(' ') + 100) + "\n" + getLabels().get(i).getText().substring(getLabels().get(i).getText().indexOf(' ') + 100);
                getLabels().get(i).setText(temp);
                System.out.println(temp);
            }
        }
    }

}
