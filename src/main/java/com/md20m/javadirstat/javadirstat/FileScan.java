package com.md20m.javadirstat.javadirstat;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class FileScan {
    private String path;
    private TreeItem pathTree;
    private double fullSize;

    public FileScan(String path){
        this.path = path;

    }

    public void Scan(){
        File file = new File(path);
        //pathTree = new TreeItem(new FileOBJ(this.path, file.length()));
        System.out.println("Name: " + file.getName() + " Length: " + file.length());
        // Check if the input path is a directory.
        if (file.isDirectory()) {
            // Display all the files in the directory and its subdirectories.

            Map<String, Long> folderSizeMap = calculateFolderSizes(file);
            long size = folderSizeMap.get(file.getName());
            this.fullSize=size;
            FileOBJ parent = new FileOBJ(this.path, size, "100%", null);
            pathTree = new TreeItem(parent);
            displayFiles(file, pathTree, folderSizeMap, parent);
        } else {
            // Display the file name and size.
            TreeItem fileAdd = new TreeItem(new FileOBJ(file.getName(), file.length(), ""));
            pathTree.getChildren().add(fileAdd);
            //System.out.println(file.getName() + " | " + file.length());
        }
    }
    private void displayFiles(File file, TreeItem folder, Map<String, Long> folderSizeMap, FileOBJ parent) {
        DecimalFormat numberFormat = new DecimalFormat("#0.00");

        File[] files = file.listFiles();
        if (files == null){
            System.out.println("This file is null");
            return;
        }
        // Iterate over the list of files and display each file name and size.
        for (File f : files) {
            //System.out.println(f.getName());
            if (f.isDirectory()) {
                // Indent the folder name.
                //System.out.println(f.getName());
                long size = folderSizeMap.get(f.getName());
                FileOBJ parent2 = new FileOBJ(f.getName(), size, (numberFormat.format(calculatePercentage(size))+"%"), parent);
                //parent.setParent(parent);
                TreeItem folder2 = new TreeItem(parent2);
                folder.getChildren().add(folder2);

                // Display the contents of the folder.
                //System.out.println(f.getName());
                displayFiles(f, folder2, folderSizeMap, parent);
            } else {
                // Indent the file name
                TreeItem fileAdd = new TreeItem(new FileOBJ(f.getName(), f.length(), (numberFormat.format(calculatePercentage(f.length()))+"%"), parent));
                folder.getChildren().add(fileAdd);
                //System.out.println(f.getName() + " | " + f.length());
            }
        }
    }

    private static Map<String, Long> calculateFolderSizes(File folder) {
        Map<String, Long> folderSizeMap = new HashMap<String, Long>();
        var folderSize = calculateFolderSizes(folder, folderSizeMap);
        folderSizeMap.put(folder.getName(), folderSize);
        return folderSizeMap;
    }


    private static long calculateFolderSizes(File folder, Map<String, Long> folderSizeMap) {
        // Get a list of all the files in the folder.
        File[] files = folder.listFiles();
        if (files == null) {
            return 0;
        }

        // Calculate the total size of the folder.
        long totalSize = 0;
        for (File f : files) {
            if (f.isDirectory()) {
                totalSize += calculateFolderSizes(f, folderSizeMap);
            } else {
                totalSize += f.length();
            }
        }

        folderSizeMap.put(folder.getName(), totalSize);
        return totalSize;
    }

    public double calculatePercentage(double part){
        if (part==0){
            return 0;
        }
        //System.out.println("Part: " + part + " Full: " + fullSize + " Percentage: " + ((part/fullSize)*100));
        return ((part/fullSize)*100);
    }

    public TreeItem getPathTree(){
        this.Scan();
        return pathTree;
    }
}