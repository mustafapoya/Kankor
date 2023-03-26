package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Config;
import net.golbarg.kankor.model.Exam;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TableConfigTest {
    static TableConfig tableConfig;

    @BeforeAll
    static void init() {
        tableConfig = new TableConfig();
        tableConfig.emptyTable();
    }

    @Test
    void testCreateExam() {
        LocalDateTime d = LocalDateTime.of(2023, 03, 26, 14, 26, 10);

        System.out.println(d.toString());

        Config config = new Config(0, "config_key", "config_value", d, d);

        tableConfig.create(config);

        ArrayList<Config> list = tableConfig.getAll();

        assertEquals(1, list.size());

        for(Config e : list) {
            System.out.println(e.toString());
        }
    }
}