package com.md20m.javadirstat.javadirstat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage s)
    {
        // set title for the stage
        s.setTitle("JavaDirStat");

        // create a menu
        Menu m = new Menu("Menu");

        // create menuitems
        MenuItem m1 = new MenuItem("menu item 1");
        MenuItem m2 = new MenuItem("menu item 2");
        MenuItem m3 = new MenuItem("menu item 3");

        // add menu items to menu
        m.getItems().add(m1);
        m.getItems().add(m2);
        m.getItems().add(m3);

        // create a menubar
        MenuBar mb = new MenuBar();

        // add menu to menubar
        mb.getMenus().add(m);

        // create a VBox
        VBox vb = new VBox(mb);

        /*TreeItem<String> rootItem = new TreeItem<String> ("Inbox");
        rootItem.setExpanded(true);
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<String> ("Message" + i);
            rootItem.getChildren().add(item);
        }
        TreeView<String> tree = new TreeView<String> (rootItem);
        StackPane root = new StackPane();
        root.getChildren().add(tree);*/
        FileTree tree = new FileTree();
        TreeTableView<FileOBJ> treeData = tree.getTree("C:\\Users\\Maj\\AppData\\Roaming\\.minecraft");
        vb.getChildren().add(treeData);

        TextField t = new TextField();

        Button b = new Button("Scan");

        b.setOnAction(event -> {
            vb.getChildren().set(1, tree.getTree(t.getText()));
        });
        vb.getChildren().add(t);
        vb.getChildren().add(b);
        // create a scene
        Scene sc = new Scene(vb, 500, 300);

        // set the scene
        s.setScene(sc);

        s.show();
    }

    public static void main(String[] args) {
        launch();
    }
}