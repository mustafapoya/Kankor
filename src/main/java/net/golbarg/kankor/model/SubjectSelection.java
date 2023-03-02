package net.golbarg.kankor.model;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class SubjectSelection {
    private QuestionSubject subject;
    private CheckBox checkBox;
    private Spinner<Integer> spinner;
    private Label label;

    public SubjectSelection(QuestionSubject subject, CheckBox checkBox, Spinner<Integer> spinner, Label label) {
        this.subject = subject;
        this.checkBox = checkBox;
        this.spinner = spinner;
        this.label = label;
    }

    public QuestionSubject getSubject() {
        return subject;
    }

    public void setSubject(QuestionSubject subject) {
        this.subject = subject;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public Spinner<Integer> getSpinner() {
        return spinner;
    }

    public void setSpinner(Spinner<Integer> spinner) {
        this.spinner = spinner;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "SubjectSelection{" +
                "subject='" + subject + '\'' +
                '}';
    }
}
