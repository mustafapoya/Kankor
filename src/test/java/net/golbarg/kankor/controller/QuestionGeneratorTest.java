package net.golbarg.kankor.controller;

import net.golbarg.kankor.db.TableQuestion;
import net.golbarg.kankor.model.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionGeneratorTest {

    @Test
    void getMathQuestions() {
        TableQuestion tableQuestion = new TableQuestion();
        tableQuestion.getQuestionsOf(QuestionGenerator.MATHEMATICS, 30);
    }
}