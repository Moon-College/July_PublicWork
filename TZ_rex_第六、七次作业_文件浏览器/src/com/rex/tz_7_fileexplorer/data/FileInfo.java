package com.rex.tz_7_fileexplorer.data;


public class FileInfo
{
    private String name = null;
    private String wholePath = null;
    private boolean isFile = false;
    private boolean isPicture = false;
    public FileInfo()
    {
    }
    public FileInfo(String name, String wholePath, boolean isFile,
            boolean isPicture)
    {
        this.name = name;
        this.wholePath = wholePath;
        this.isFile = isFile;
        this.isPicture = isPicture;
    }

    public String getWholePath()
    {
        return wholePath;
    }

    public void setWholePath(String wholePath)
    {
        this.wholePath = wholePath;
    }

    public boolean isFile()
    {
        return isFile;
    }

    public void setFile(boolean isFile)
    {
        this.isFile = isFile;
    }

    public boolean isPicture()
    {
        return isPicture;
    }

    public void setPicture(boolean isPicture)
    {
        this.isPicture = isPicture;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    
    @Override
    public String toString()
    {
        return "名称：" + name + "\n全路径："+wholePath;
    }
}
