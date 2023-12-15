package com.md20m.javadirstat.javadirstat;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage s)
    {
        // set title for the stage
        s.setTitle("JavaDirStat");

        // create a menu
        Menu m = new Menu("File");

        // create menuitems
        MenuItem open = new MenuItem("Open");
        MenuItem refresh = new MenuItem("Refresh");
        MenuItem clear = new MenuItem("Clear");
        MenuItem cache = new MenuItem("Cache path");
        MenuItem deCache = new MenuItem("DeCache");



        // add menu items to menu
        m.getItems().add(open);
        m.getItems().add(refresh);
        m.getItems().add(clear);
        m.getItems().add(cache);

        // create a menubar
        MenuBar mb = new MenuBar();

        // add menu to menubar
        mb.getMenus().add(m);

        // create a VBox
        VBox vb = new VBox(mb);

        FileTree tree = new FileTree();
        TreeTableView<FileOBJ> treeData = tree.getTree();
        vb.getChildren().add(treeData);

        TextField t = new TextField();
        String savedPath;

        Button b = new Button("Scan");

        b.setOnAction(event -> {
            //savedPath = t.getText();
            vb.getChildren().set(1, tree.getTree(t.getText()));
        });

        Popup popup = new Popup();
        VBox pVB = new VBox();
        Label label = new Label("Enter the folder path you want to scan");
        pVB.setStyle("-fx-border-color: black ; -fx-border-width: 1; -fx-background-color: white; -fx-padding: 10; -fx-content-display: flex; -fx-alignment: center");
        pVB.getChildren().addAll(label, t, b);
        pVB.setSpacing(5);

        // add the label
        popup.getContent().add(pVB);
        popup.setHeight(100);
        popup.setWidth(120);
        // set auto hide
        popup.setAutoHide(true);

        // set size of label
        label.setMinWidth(80);
        label.setMinHeight(50);

        // action event
        open.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                if (!popup.isShowing())
                    popup.show(s);
                else
                    popup.hide();
            }
        });
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                vb.getChildren().set(1, tree.getTree(t.getText()));
            }
        });
        clear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                vb.getChildren().set(1, tree.getTree(null));
            }
        });
        // CACHING IN DEVELOPMENT
        cache.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                DirectoryChooser chooser = new DirectoryChooser();
                chooser.setTitle("JavaFX Projects");

                File defaultDirectory = new File("c:/");
                chooser.setInitialDirectory(defaultDirectory);
                File selectedDirectory = chooser.showDialog(s);
                String cPath = selectedDirectory.getAbsolutePath();
                System.out.println(cPath);
                Serializer s = new Serializer();
                try {
                    s.serialise(tree.getRoot());
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        //vb.getChildren().add(t);
        //vb.getChildren().add(b);
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