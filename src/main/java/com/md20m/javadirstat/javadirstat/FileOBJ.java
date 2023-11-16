package com.md20m.javadirstat.javadirstat;

public class FileOBJ {
    protected long size;
    protected String name;
    protected String percentage;
    public FileOBJ (String name, long size, String procentage){
        this.name = name;
        this.size = size;
        this.percentage = procentage;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public String getPercentage(){
        return percentage;
    }
}
