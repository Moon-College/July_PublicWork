package com.hacket.listviewhomework1.bean;

/**
 * Created by zengfansheng on 2015/7/24 0024.
 */
public class Person {
    private int icon;
    private String nick;
    private String gender;
    private String beauty;
    private String hobby;

    public Person() {
    }

    public Person(String nick, int icon, String gender, String beauty, String hobby) {
        this.nick = nick;
        this.icon = icon;
        this.gender = gender;
        this.beauty = beauty;
        this.hobby = hobby;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBeauty() {
        return beauty;
    }

    public void setBeauty(String beauty) {
        this.beauty = beauty;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
