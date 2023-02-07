package net.golbarg.kankor.controller;

import net.golbarg.kankor.db.TableUser;
import net.golbarg.kankor.model.User;

public class SystemController {
    public static User currentUser = ConfigurationController.IS_PUBLISHED_MODE ? null : tempLogin();

    private static User tempLogin() {
        TableUser tableUser = new TableUser();
        User user = tableUser.authUser("Mustafa", "123456");
        return user;
    }


    public static void main(String[] args) {
        System.out.println(SystemController.currentUser.getName());
    }
}
