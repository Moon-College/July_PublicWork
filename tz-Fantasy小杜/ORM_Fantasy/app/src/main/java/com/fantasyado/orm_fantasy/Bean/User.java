package com.fantasyado.orm_fantasy.Bean;

/**
 * Created by Administrator on 2015/10/3 0003.
 */
public class User {
    private int userID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    private String userName;
    private int age;
    private String sex;

    public User(String userName, String sex, int age) {
        this.userName = userName;
        this.sex = sex;
        this.age = age;
    }
    public User(){

    }
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ",userID = '"+userID+'\''+
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
