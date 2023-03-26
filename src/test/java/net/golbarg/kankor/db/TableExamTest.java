package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Exam;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TableExamTest {
    static TableExam tableExam;

    @BeforeAll
    static void init() {
        tableExam = new TableExam();
        tableExam.emptyTable();
    }

    @Test
    void testGetAll() throws ParseException {
        ArrayList<Exam> exams = tableExam.getAll();
        assertEquals(0, exams.size());
    }

    @Test
    void testCreateExam() {
        ArrayList<Exam> exams = tableExam.getAll();

        LocalDate d = LocalDate.of(2023, 03, 26);

        Exam exam = new Exam(0, 1, d, 100, 30 , 40,
                70, 100, "Computer Science");

        assertEquals(240, exam.getTotalScore());

        tableExam.create(exam);

        exam = new Exam(0, 1, d, 120, 50 , 50,
                70, 100, "Computer Science");

        tableExam.create(exam);

        exams = tableExam.getAll();

        assertEquals(2, exams.size());

        for(Exam e : exams) {
            System.out.println(e.toString());
        }
    }
}