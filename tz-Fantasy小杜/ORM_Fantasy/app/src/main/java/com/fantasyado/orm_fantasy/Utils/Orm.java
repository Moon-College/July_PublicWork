package com.fantasyado.orm_fantasy.Utils;

import com.fantasyado.orm_fantasy.Bean.Item;
import com.fantasyado.orm_fantasy.Bean.Key;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/10/3 0003.
 */
public class Orm {

    private String tableName;
    private String beanName;
    private String daoName;
    private Key key;
    private ArrayList<Item> item = new ArrayList<Item>();


    @Override
    public String toString() {
        return "Orm{" +
                "tableName='" + tableName + '\'' +
                ", beanName='" + beanName + '\'' +
                ", daoName='" + daoName + '\'' +
                ", key=" + key +
                ", item=" + item +
                '}';
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getDaoName() {
        return daoName;
    }

    public void setDaoName(String daoName) {
        this.daoName = daoName;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public ArrayList<Item> getItem() {
        return item;
    }

    public void setItem(ArrayList<Item> item) {
        this.item = item;
    }
}
