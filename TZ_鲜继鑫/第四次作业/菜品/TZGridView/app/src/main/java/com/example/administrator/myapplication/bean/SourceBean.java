package com.example.administrator.myapplication.bean;

/**
 * Created by Administrator on 2015/7/23.
 */
public class SourceBean {
    private int food;
    private int header;
    private String name;
    private int num;
    private String content;

    public String getContent() {
        return content;
    }

    public int getFood() {
        return food;
    }

    public int getHeader() {
        return header;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public SourceBean(int food, int header, String name, int num,String content) {

        this.food = food;
        this.header = header;
        this.name = name;
        this.num = num;
        this.content = content;
    }
}
