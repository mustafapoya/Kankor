package net.golbarg.kankor.db;

import net.golbarg.kankor.controller.SystemController;
import net.golbarg.kankor.model.Exam;
import net.golbarg.kankor.model.ExamCorrectAnswerCount;
import net.golbarg.kankor.model.ExamResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TableExamResultTestResult {
    static TableExamResult tableExamResult;

    @BeforeAll
    static void init() {
        tableExamResult = new TableExamResult();
        tableExamResult.emptyTable();
    }

    @Test
    void testGetAll() throws ParseException {
        ArrayList<ExamResult> examResults = tableExamResult.getAll();
        assertEquals(0, examResults.size());
    }

    @Test
    void testCreateExam() {
        ArrayList<ExamResult> examResults = tableExamResult.getAll();

        long duration = 1000;

        Exam exam = SystemController.DEFAULT_EXAM;
        ExamCorrectAnswerCount correctAnswerCount = new ExamCorrectAnswerCount(30 , 40, 70, 100);

        ExamResult examResult = new ExamResult(0, exam, duration, correctAnswerCount, "Computer Science");

        assertEquals(240, examResult.getCorrectAnswerCount().getScore());

        tableExamResult.create(examResult);

        correctAnswerCount = new ExamCorrectAnswerCount(30 , 40, 70, 100);
        examResult = new ExamResult(0, exam, duration, correctAnswerCount, "Computer Science");

        tableExamResult.create(examResult);

        examResults = tableExamResult.getAll();

        assertEquals(2, examResults.size());

        for(ExamResult e : examResults) {
            System.out.println(e.toString());
        }
    }
}