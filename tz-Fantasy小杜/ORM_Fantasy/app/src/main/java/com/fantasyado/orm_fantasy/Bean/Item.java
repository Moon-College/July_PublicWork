package com.fantasyado.orm_fantasy.Bean;

/**
 * Created by Administrator on 2015/10/3 0003.
 */
public class Item {

    public Item(){

    }

    private  String column;
    private String property;
    private String type;

    public Item(String column, String property, String type) {
        this.column = column;
        this.property = property;
        this.type = type;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Item{" +
                "column='" + column + '\'' +
                ", property='" + property + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
