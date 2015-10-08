package com.fantasyado.orm_fantasy.Utils;

import android.util.Xml;

import com.fantasyado.orm_fantasy.Bean.Item;
import com.fantasyado.orm_fantasy.Bean.Key;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/3 0003.
 */
public class ormTemplate {
    public static Map<String, Orm> OrmConfig = new HashMap<String, Orm>();

    public static Orm parseOrm(InputStream in) throws  Exception {

        Orm orm = null;

            XmlPullParser parser = (XmlPullParser) Xml.newPullParser();
            parser.setInput(in, "utf-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                String tagName = parser.getName();
                switch (eventType) {

                    case XmlPullParser.START_TAG:
                        if (tagName.equals("orm")) {
                            orm = new Orm();
                            orm.setTableName(parser.getAttributeValue(null, "tablename"));
                            orm.setBeanName(parser.getAttributeValue(null, "beanName"));
                            orm.setDaoName(parser.getAttributeValue(null, "daoName"));

                        } else if (tagName.equals("key")) {
                            Key key = new Key();
                            key.setColumn(parser.getAttributeValue(null, "column"));
                            key.setProperty(parser.getAttributeValue(null, "property"));
                            key.setType(parser.getAttributeValue(null, "type"));
                            key.setIsAutoIncrease(Boolean.parseBoolean(parser.getAttributeValue(null, "identity")));
                            orm.setKey(key);
                        } else if (tagName.equals("item")) {
                            Item item = new Item();
                            item.setColumn(parser.getAttributeValue(null, "column"));
                            item.setType(parser.getAttributeValue(null, "type"));
                            item.setProperty(parser.getAttributeValue(null, "property"));
                            orm.getItem().add(item);

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
