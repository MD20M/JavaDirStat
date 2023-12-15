package com.md20m.javadirstat.javadirstat;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.stage.FileChooser;

import java.awt.Desktop;
import java.io.*;

public class Serializer {
    public void serialise(TreeItem<FileOBJ> f) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream( "Save.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(f);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public TreeTableView<FileOBJ> deserialise(File f) throws IOException, ClassNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(f);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        TreeTableView<FileOBJ> tree = (TreeTableView<FileOBJ>) objectInputStream.readObject();
        objectInputStream.close();
        return tree;
    }
}
