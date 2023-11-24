package com.md20m.javadirstat.javadirstat;

public class FileOBJ {
    protected long size;
    protected String betterSize;
    protected String name;
    protected String percentage;
    protected double numPercentage;
    protected FileOBJ parent;

    public FileOBJ (String name, long size, String percentage){
        this.name = name;
        this.size = size;
        this.percentage = percentage;
        this.numPercentage = this.getNumPercentage();
    }
    public FileOBJ (String name, long size, String percentage, FileOBJ parent){
        this.name = name;
        this.size = size;
        this.betterSize = this.convertBytesToString();
        System.out.println(betterSize);
        this.percentage = percentage;
        this.numPercentage = this.getNumPercentage();
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public String getBetterSize() { return betterSize; }

    public String getPercentage(){
        return percentage;
    }

    public double getNumPercentageValue() { return numPercentage; }

    public double getNumPercentage() {
        //StringBuilder p = new StringBuilder(this.percentage);
        //p.deleteCharAt(p.length()-1);
        //System.out.println(Double.parseDouble(p.toString())/100);
        //return (Double.parseDouble(p.toString()));
        if (parent==null) return 1;
        //System.out.println((((double)(this.getSize()))/((double)(parent.getSize()))) + "P: " + this.parent.getName() + "C: " + this.name);
        return ((((double)(this.getSize()))/(parent.getSize())));
    }

    public String convertBytesToString() {
        double bytes = this.size;
        if (bytes < 1024) {
            return String.format("%.2f B", bytes);
        } else if (bytes < 1048576) {
            return String.format("%.2f KB", bytes / 1024.0);
        } else if (bytes < 1073741824) {
            return String.format("%.2f MB", bytes / 1048576.0);
        } else {
            return String.format("%.2f GB", bytes / 1073741824.0);
        }
    }


    public void  setParent(FileOBJ parent) { this.parent=parent; }
}
