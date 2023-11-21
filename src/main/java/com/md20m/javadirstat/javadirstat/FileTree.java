package com.md20m.javadirstat.javadirstat;

import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

public class FileTree {
    private TreeItem root;
    private TreeTableView<FileOBJ> treeTableView;

    private TreeTableColumn<FileOBJ, String> name;
    private TreeTableColumn<FileOBJ, String> size;
    private TreeTableColumn<FileOBJ, String> percentage;
    TreeTableColumn<FileOBJ, Double> progressColumn;
    public FileTree(){
        treeTableView = new TreeTableView<FileOBJ>();
        name = new TreeTableColumn<>("Name");
        size = new TreeTableColumn<>("Size");
        percentage = new TreeTableColumn<>("Percentage");
        progressColumn = new TreeTableColumn<>("Graph");
        name.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        size.setCellValueFactory(new TreeItemPropertyValueFactory<>("size"));
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
    }

    public TreeTableView<FileOBJ> getTree(String path){
        FileScan sc = new FileScan(path);
        root = sc.getPathTree();
        treeTableView.setRoot(root);
        return treeTableView;
    }

    public void setTree(String path){
        FileScan sc = new FileScan(path);
        root = sc.getPathTree();
    }
}
