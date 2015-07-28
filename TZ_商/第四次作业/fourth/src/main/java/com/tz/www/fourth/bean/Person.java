package com.tz.www.fourth.bean;

import android.graphics.Bitmap;

/**
 * 项目名称：First
 * 类描述：
 * 创建人：Shang Wentong
 * 创建时间：2015/7/28 17:42
 * 修改人：Shang Wentong
 * 修改时间：2015/7/28 17:42
 * 修改备注：
 */
public class Person {
    private  String name,sex, hobby,yanZhi;
    private Bitmap bitmap;

    public Person(String name, String sex, String hobby, String yanZhi, Bitmap bitmap) {
        this.name = name;
        this.sex = sex;
        this.hobby = hobby;
        this.yanZhi = yanZhi;
        this.bitmap = bitmap;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", hobby='" + hobby + '\'' +
                ", yanZhi='" + yanZhi + '\'' +
                ", bitmap=" + bitmap +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getYanZhi() {
        return yanZhi;
    }

    public void setYanZhi(String yanZhi) {
        this.yanZhi = yanZhi;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
