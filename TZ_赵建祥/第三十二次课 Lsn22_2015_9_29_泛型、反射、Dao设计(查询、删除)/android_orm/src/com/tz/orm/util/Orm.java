package com.tz.orm.util;

import java.util.ArrayList;
import java.util.List;

public class Orm {

	private String beanCls="";
	private String beanTab="";
	private String beanDao="";
	private Key key;
	private List<Item> items;
	
	public Orm(){
		items=null;
		items=new ArrayList<Item>();
	}

	public String getBeanCls() {
		return beanCls;
	}

	public void setBeanCls(String beanCls) {
		this.beanCls = beanCls;
	}

	public String getBeanTab() {
		return beanTab;
	}

	public void setBeanTab(String beanTab) {
		this.beanTab = beanTab;
	}

	public String getBeanDao() {
		return beanDao;
	}

	public void setBeanDao(String beanDao) {
		this.beanDao = beanDao;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Orm [beanCls=" + beanCls + ", beanTab=" + beanTab
				+ ", beanDao=" + beanDao + ", key=" + key + ", items=" + items
				+ "]";
	}
}
