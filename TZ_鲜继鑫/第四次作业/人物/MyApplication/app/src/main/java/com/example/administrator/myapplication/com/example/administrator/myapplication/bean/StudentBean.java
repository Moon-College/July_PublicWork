package com.example.administrator.myapplication.com.example.administrator.myapplication.bean;

import android.widget.ImageView;

/**
 * Created by Administrator on 2015/7/23.
 */
public class StudentBean {
    private int header;
    private String name ;
    private String sex;
    private String yanzhi;
    private String hobby;
    private int color;

    public int getColor() {
        return color;
    }


    public StudentBean(int header, String name, String sex, String yanzhi, String hobby,int color) {
        this.header = header;
        this.name = name;
        this.sex = sex;
        this.yanzhi = yanzhi;
        this.hobby = hobby;
        this.color = color;
    }

    public int getHeader() {
        return header;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getYanzhi() {
        return yanzhi;
    }

    public String getHobby() {
        return hobby;
    }
}
