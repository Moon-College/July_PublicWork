package com.tz.orm.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class TempletConfig {
	public static Map<String,Orm> orms=new HashMap<String,Orm>();
	
	/**
	 * 从流中解析XML文件，生成Orm对象
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static Orm parseXmlToOrm(InputStream is) throws Exception {
		Orm orm = null;
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "utf-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			String tagName = parser.getName();
			switch (eventType) {
			case XmlPullParser.START_TAG:
				if (tagName.equals("bean")) {
					orm = new Orm();
					orm.setBeanCls(parser.getAttributeValue(null,"beanCls"));
					orm.setBeanTab(parser.getAttributeValue(null,"beanTab"));
					orm.setBeanDao(parser.getAttributeValue(null,"beanDao"));
				} else if (tagName.equals("key")) {
					Key key = new Key();
					key.setColumn(parser.getAttributeValue(null,"column"));
					key.setProperty(parser.getAttributeValue(null,"property"));
					key.setType(parser.getAttributeValue(null,"type"));
					key.setIdentity(Boolean.parseBoolean((parser
							.getAttributeValue(null,"identity"))));
					orm.setKey(key);
				} else if (tagName.equals("item")) {
					Item item = new Item();
					item.setColumn(parser.getAttributeValue(null,"column"));
					item.setProperty(parser.getAttributeValue(null,"property"));
					item.setType(parser.getAttributeValue(null,"type"));
					orm.getItems().add(item);
				}
				break;
			default:
				break;
			}
			eventType = parser.next();
		}
		return orm;
	}
}
