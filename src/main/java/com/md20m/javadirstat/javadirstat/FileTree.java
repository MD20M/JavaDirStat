package com.md20m.javadirstat.javadirstat;

import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;

public class FileTree {
    private TreeItem root;
    private TreeTableView<FileOBJ> treeTableView;

    private TreeTableColumn<FileOBJ, String> name;
    private TreeTableColumn<FileOBJ, String> size;
    private TreeTableColumn<FileOBJ, String> percentage;
    private TreeTableColumn<FileOBJ, Double> progressColumn;
    public FileTree(){
        treeTableView = new TreeTableView<FileOBJ>();
        name = new TreeTableColumn<>("Name");
        size = new TreeTableColumn<>("Size");
        percentage = new TreeTableColumn<>("Percentage");
        progressColumn = new TreeTableColumn<>("Graph");
        name.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        size.setCellValueFactory(new TreeItemPropertyValueFactory<>("betterSize"));
        //size.setComparator(Comparator.comparing());
        percentage.setCellValueFactory(new TreeItemPropertyValueFactory<>("percentage"));
        progressColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("numPercentage"));

        progressColumn.setCellFactory(column -> new TreeTableCell<>() {
            final ProgressBar progressBar = new ProgressBar();
            @Override
            protected void updateItem(Double item, boolean empty) {
                //System.out.println(item);
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    progressBar.setProgress(item);
                    setGraphic(progressBar);
                }
            }
        });

        treeTableView.getColumns().add(name);
        treeTableView.getColumns().add(size);
        treeTableView.getColumns().add(percentage);
        treeTableView.getColumns().add(progressColumn);
        treeTableView.setContextMenu(getContextMenu()); // Add context menu to tree view
    }

    public TreeTableView<FileOBJ> getTree(String path){
        FileScan sc = new FileScan(path);
        root = sc.getPathTree();
        treeTableView.setRoot(root);
        return treeTableView;
    }

    public TreeTableView<FileOBJ> getTree(){
        FileScan sc = new FileScan(null);
        root = sc.getPathTree();
        treeTableView.setRoot(root);
        return treeTableView;
    }

    public void setTree(String path){
        FileScan sc = new FileScan(path);
        root = sc.getPathTree();
    }

    private ContextMenu getContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem openFile = new MenuItem("Open File");
        openFile.setOnAction(event -> {
            TreeItem<FileOBJ> selectedTreeItem = treeTableView.getSelectionModel().getSelectedItem();
            if (selectedTreeItem != null) {
                FileOBJ fileOBJ = selectedTreeItem.getValue();
                // Open the file here
                try {
                    System.out.println(fileOBJ.getPath());
                    Desktop.getDesktop().open(new File(fileOBJ.getPath()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Opening file: " + fileOBJ.getName());
            }
        });
        MenuItem deleteFile = new MenuItem("Delete File");
        deleteFile.setOnAction(event -> {
            TreeItem<FileOBJ> selectedTreeItem = treeTableView.getSelectionModel().getSelectedItem();
            if (selectedTreeItem != null) {
                FileOBJ fileOBJ = selectedTreeItem.getValue();
                // Open the file here
                try {
                    System.out.println(fileOBJ.getPath());
                    File f = new File(fileOBJ.getPath());
                    f.delete();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Opening file: " + fileOBJ.getName());
            }
        });
        contextMenu.getItems().addAll(openFile, deleteFile);
        return contextMenu;
    }
}
