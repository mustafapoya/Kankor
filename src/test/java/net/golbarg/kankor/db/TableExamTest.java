package net.golbarg.kankor.db;

import net.golbarg.kankor.controller.SystemController;
import net.golbarg.kankor.model.Exam;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableExamTest {

    @Test
    void create() {
        Exam exam = SystemController.DEFAULT_EXAM;
        new TableExam().create(exam);
        System.out.println(exam);
    }
}