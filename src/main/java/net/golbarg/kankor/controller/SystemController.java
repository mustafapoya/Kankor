package net.golbarg.kankor.controller;

import net.golbarg.kankor.db.TableUser;
import net.golbarg.kankor.model.Exam;
import net.golbarg.kankor.model.User;

import java.time.LocalDate;

public class SystemController {
    public static User currentUser = ConfigurationController.IS_PUBLISHED_MODE ? null : tempLogin();
    public static final String SERVER_ADDRESS = "http://localhost:8100/";

    public static final Exam DEFAULT_EXAM = new Exam(0, currentUser, LocalDate.now(), 46, 40, 38, 36);

    private static User tempLogin() {
        TableUser tableUser = new TableUser();
        User user = tableUser.authUser("Mustafa", "123456");
        return user;
    }

}
