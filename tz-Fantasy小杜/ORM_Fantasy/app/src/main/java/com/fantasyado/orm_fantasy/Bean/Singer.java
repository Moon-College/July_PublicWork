package com.fantasyado.orm_fantasy.Bean;

/**
 * Created by Administrator on 2015/10/3 0003.
 */
public class Singer {

    private String singer;
    private String best_songs;
    private int age;
    private int userID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getBest_songs() {
        return best_songs;
    }

    public void setBest_songs(String best_songs) {
        this.best_songs = best_songs;
    }

    public Singer(String singer, String bset_songs, int age) {
        this.singer = singer;
        this.best_songs = bset_songs;
        this.age = age;
    }
    public Singer(){

    }

    @Override
    public String toString() {
        return "Singer{" +
                "singer='" + singer + '\'' +
                ", best_songs='" + best_songs + '\'' +
                ", age=" + age +
                ",id ="+userID+
                '}';
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
