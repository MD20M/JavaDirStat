package com.md20m.javadirstat.javadirstat;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

public class FileTree {
    private TreeItem root;
    private TreeTableView<FileOBJ> treeTableView;

    private TreeTableColumn<FileOBJ, String> treeTableColumn1;
    private TreeTableColumn<FileOBJ, String> treeTableColumn2;
    private TreeTableColumn<FileOBJ, String> treeTableColumn3;
    public FileTree(){
        treeTableView = new TreeTableView<FileOBJ>();
        treeTableColumn1 = new TreeTableColumn<>("Name");
        treeTableColumn2= new TreeTableColumn<>("Size");
        treeTableColumn3= new TreeTableColumn<>("Percentage");
        treeTableColumn1.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        treeTableColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("size"));
        treeTableColumn3.setCellValueFactory(new TreeItemPropertyValueFactory<>("percentage"));

        treeTableView.getColumns().add(treeTableColumn1);
        treeTableView.getColumns().add(treeTableColumn2);
        treeTableView.getColumns().add(treeTableColumn3);
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
