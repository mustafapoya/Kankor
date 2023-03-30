package net.golbarg.kankor.controller.ui;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.List;

public class UIController {

    public static void enableTab(TabPane tabPane, List<Tab> tabs, int index) {
        for(int i = 0; i < tabs.size(); i++) {
            if(index == i) {
                tabs.get(i).setDisable(false);
                tabPane.getSelectionModel().select(i);
            } else {
                tabs.get(i).setDisable(true);
            }
        }
    }
}
