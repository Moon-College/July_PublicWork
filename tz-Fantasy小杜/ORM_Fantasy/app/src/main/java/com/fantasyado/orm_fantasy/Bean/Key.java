package com.fantasyado.orm_fantasy.Bean;

/**
 * Created by Administrator on 2015/10/3 0003.
 */
public class Key extends Item {

    public Key(){
        super();
    }

    public Key(String column, String property, String type, boolean isAutoIncrease) {
        super(column, property, type);
        this.isAutoIncrease = isAutoIncrease;
    }

    private boolean isAutoIncrease ;

    public boolean isAutoIncrease(boolean identity) {
        return isAutoIncrease;
    }

    public void setIsAutoIncrease(boolean isAutoIncrease) {
        this.isAutoIncrease = isAutoIncrease;
    }
}
