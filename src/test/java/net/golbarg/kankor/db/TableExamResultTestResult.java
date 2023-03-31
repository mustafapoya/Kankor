package net.golbarg.kankor.db;

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

        LocalDate d = LocalDate.of(2023, 03, 26);

        ExamResult examResult = new ExamResult(0, 1, d, 100, 30 , 40,
                70, 100, "Computer Science");

        assertEquals(240, examResult.getTotalScore());

        tableExamResult.create(examResult);

        examResult = new ExamResult(0, 1, d, 120, 50 , 50,
                70, 100, "Computer Science");

        tableExamResult.create(examResult);

        examResults = tableExamResult.getAll();

        assertEquals(2, examResults.size());

        for(ExamResult e : examResults) {
            System.out.println(e.toString());
        }
    }
}