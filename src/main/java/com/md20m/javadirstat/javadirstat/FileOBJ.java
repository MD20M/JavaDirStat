package com.md20m.javadirstat.javadirstat;

public class FileOBJ {
    protected long size;
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

    public String getPercentage(){
        return percentage;
    }

    public double getNumPercentage() {
        //StringBuilder p = new StringBuilder(this.percentage);
        //p.deleteCharAt(p.length()-1);
        //System.out.println(Double.parseDouble(p.toString())/100);
        //return (Double.parseDouble(p.toString()));
        if (parent==null) return 1;
        System.out.println((((double)(this.getSize()))/((double)(parent.getSize()))) + "P: " + this.parent.getName() + "C: " + this.name);
        return ((((double)(this.getSize()))/(parent.getSize())));
    }

    public void  setParent(FileOBJ parent) { this.parent=parent; }
}
