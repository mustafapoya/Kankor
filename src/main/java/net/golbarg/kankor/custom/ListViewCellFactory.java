package net.golbarg.kankor.custom;


import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import net.golbarg.kankor.model.IListViewCell;
import net.golbarg.kankor.model.Resource;
import org.kordamp.ikonli.javafx.FontIcon;

public class ListViewCellFactory<ListItemClass extends IListViewCell> {
    private String icon;
    private int iconSize;

    public ListViewCellFactory() {
        this("bi-list", 14);
    }

    public ListViewCellFactory(String icon, int iconSize) {
        this.icon = icon;
        this.iconSize = iconSize;
    }

    public Callback<ListView<ListItemClass>, ListCell<ListItemClass>> createCellFactory() {
        return new Callback<ListView<ListItemClass>, ListCell<ListItemClass>>() {
            @Override
            public ListCell<ListItemClass> call(ListView<ListItemClass> param) {
                final Label leadLbl = new Label();
                FontIcon listIcon = new FontIcon(icon);
                listIcon.setIconSize(iconSize);

                final ListCell<ListItemClass> cell = new ListCell<ListItemClass>() {
                    @Override
                    protected void updateItem(ListItemClass item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null) {
                            leadLbl.setText(item.getListItemText().trim());
                            setText(item.getListItemText().trim());
                            setGraphic(listIcon);
                        } else {
                            leadLbl.setText("");
                            setText("");
                            setGraphic(null);
                        }
                    }
                };
                return cell;
            }
        };
    }
}
