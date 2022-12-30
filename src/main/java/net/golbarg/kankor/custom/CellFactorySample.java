package net.golbarg.kankor.custom;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import net.golbarg.kankor.model.Gender;
import net.golbarg.kankor.model.Language;
import net.golbarg.kankor.model.Location;
import org.kordamp.ikonli.javafx.FontIcon;

public class CellFactorySample {

    public static Callback<ListView<Location>, ListCell<Location>> getComboBoxLocation(String icon, int iconSize) {
        Callback<ListView<Location>, ListCell<Location>> callback = new Callback<>() {
            @Override
            public ListCell call(ListView<Location> param) {
                final Label lblLead = new Label();
                FontIcon iconLocation = new FontIcon((icon == null || icon.isEmpty()) ? "fas-map-marker-alt" : icon);
                iconLocation.setIconSize(iconSize < 1 ? 14 : iconSize);

                final ListCell<Location> cell = new ListCell<Location>() {
                    @Override
                    protected void updateItem(Location item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            lblLead.setText(item.getPersianName());
                            setText(item.getPersianName());
                            setGraphic(iconLocation);
                        } else {
                            lblLead.setText("");
                            setText("");
                            setGraphic(null);
                        }
                    }
                };

                return cell;
            }
        };
        return callback;
    }

    public static Callback<ListView<Gender>, ListCell<Gender>> getComboBoxGender(String icon, int iconSize) {
        Callback<ListView<Gender>, ListCell<Gender>> cellFactory = new Callback<ListView<Gender>, ListCell<Gender>>() {
            @Override
            public ListCell call(ListView<Gender> param) {
                final Label lblLead = new Label();
                FontIcon iconGender = new FontIcon((icon == null || icon.isEmpty()) ? "fab-odnoklassniki" : icon);
                iconGender.setIconSize(iconSize < 1 ? 14 : iconSize);

                final ListCell<Gender> cell = new ListCell<Gender>(){
                    @Override
                    protected void updateItem(Gender item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null) {
                            lblLead.setText(item.getLabel());
                            setText(item.getLabel());
                            setGraphic(iconGender);
                        } else {
                            lblLead.setText("");
                            setText("");
                            setGraphic(null);
                        }
                    }
                };

                return cell;
            }
        };

        return cellFactory;
    }

    public static Callback<ListView<Language>, ListCell<Language>> getComboBoxLanguage(String icon, int iconSize) {
        Callback<ListView<Language>, ListCell<Language>> cellFactory = new Callback<ListView<Language>, ListCell<Language>>() {
            @Override
            public ListCell call(ListView<Language> param) {
                final Label lblLead = new Label();
                FontIcon iconLanguage = new FontIcon((icon == null || icon.isEmpty()) ? "fas-language" : icon);
                iconLanguage.setIconSize(iconSize < 1 ? 14 : iconSize);

                final ListCell<Language> cell = new ListCell<Language>(){
                    @Override
                    protected void updateItem(Language item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null) {
                            lblLead.setText(item.getLabel());
                            setText(item.getLabel());
                            setGraphic(iconLanguage);
                        } else {
                            lblLead.setText("");
                            setText("");
                            setGraphic(null);
                        }
                    }
                };

                return cell;
            }
        };

        return cellFactory;
    }
}
