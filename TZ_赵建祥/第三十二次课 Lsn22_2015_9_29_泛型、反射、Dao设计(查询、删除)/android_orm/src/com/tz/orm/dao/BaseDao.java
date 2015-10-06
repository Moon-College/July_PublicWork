package com.tz.orm.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tz.orm.util.Item;
import com.tz.orm.util.Key;
import com.tz.orm.util.MySqliteOpenHelper;
import com.tz.orm.util.Orm;
import com.tz.orm.util.TempletConfig;

public class BaseDao<T> {
	private MySqliteOpenHelper dbHelper = null;
	private Class beanCls;

	public BaseDao(MySqliteOpenHelper dbHelper, Class beanCls) {
		super();
		this.dbHelper = dbHelper;
		this.beanCls = beanCls;
	}

	public long insert(T t) throws Exception {
		long id = -1;
		Orm orm = TempletConfig.orms.get(beanCls.getSimpleName() + ".orm.xml");
		if (orm != null) {
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			Key key = orm.getKey();
			List<Item> items = orm.getItems();
			Class beanCls = Class.forName(orm.getBeanCls());
			// 主键如果不是自动增长，则需要手动设置其值
			if (!key.isIdentity()) {
				String column = key.getColumn();
				String property = key.getProperty();
				String type = key.getType();
				// 从T中获取属性property的值
				Field field = beanCls.getDeclaredField(property);
				field.setAccessible(true);
				Object propertyValue = field.get(t);
				// 反射调用values的put方法
				Method method = ContentValues.class.getMethod("put",
						new Class[] { String.class, Class.forName(type) });
				method.invoke(values, new Object[] { column, propertyValue });
			}
			// 设置其他属性（非主键）
			if (items != null) {
				for (Item item : items) {
					String column = item.getColumn();
					String property = item.getProperty();
					String type = item.getType();
					// 从T中获取属性property的值
					Field field = beanCls.getDeclaredField(property);
					field.setAccessible(true);
					Object propertyValue = field.get(t);
					// 反射调用values的put方法
					Method method = ContentValues.class.getMethod("put",
							new Class[] { String.class, Class.forName(type) });
					method.invoke(values,
							new Object[] { column, propertyValue });
				}
			}
			id = db.insert(orm.getBeanTab(), null, values);
		}
		return id;
	}

	public List<T> queryAll() throws Exception {
		List<T> list = new ArrayList<T>();
		long id = -1;
		Orm orm = TempletConfig.orms.get(beanCls.getSimpleName() + ".orm.xml");
		if (orm != null) {
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			Cursor cursor = db.query(true, orm.getBeanTab(), null, null, null,
					null, null, null, null);
			while (cursor.moveToNext()) {
				// 实例化
				Class beanCls = Class.forName(orm.getBeanCls());
				Constructor<T> beanConstructor = beanCls.getConstructor(null);
				T t = beanConstructor.newInstance(null);
				// 获取主键
				String keyColumn = orm.getKey().getColumn();
				String keyProperty = orm.getKey().getProperty();
				String keyType = orm.getKey().getType();
				int columnIndex = cursor.getColumnIndex(keyColumn);
				Class keyTypeCls = Class.forName(keyType);
				Method keyMethod = null;
				if (keyTypeCls.getSimpleName().equals(
						Integer.class.getSimpleName())) {
					keyMethod = Cursor.class.getDeclaredMethod("getInt",
							new Class[] { int.class });
				} else {
					keyMethod = Cursor.class.getDeclaredMethod("get"
							+ keyTypeCls.getSimpleName(),
							new Class[] { int.class });
				}
				Object keyValue = keyMethod.invoke(cursor, columnIndex);
				// 反射给t赋值
				Field keyField = beanCls.getDeclaredField(keyProperty);
				keyField.setAccessible(true);
				keyField.set(t, keyValue);
				// 给普通属性赋值
				for (Item item : orm.getItems()) {
					String itemColumn = item.getColumn();
					String itemProperty = item.getProperty();
					String itemType = item.getType();
					int itemColumnIndex = cursor.getColumnIndex(itemColumn);
					Class itemTypeCls = Class.forName(itemType);
					Method itemMethod = Cursor.class.getDeclaredMethod("get"
							+ itemTypeCls.getSimpleName(),
							new Class[] { int.class });
					Object itemValue = itemMethod.invoke(cursor,
							itemColumnIndex);
					// 反射给t赋值
					Field itemField = beanCls.getDeclaredField(itemProperty);
					itemField.setAccessible(true);
					itemField.set(t, itemValue);
				}
				list.add(t);
			}
		}
		return list;
	}
	
	public int delete(T t) throws Exception {
		int result = -1;
		Orm orm = TempletConfig.orms.get(beanCls.getSimpleName() + ".orm.xml");
		if (orm != null) {
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			String keyColumn = orm.getKey().getColumn();
			String keyProperty = orm.getKey().getProperty();
			Field keyField=beanCls.getDeclaredField(keyProperty);
			keyField.setAccessible(true);
			Object keyValue=keyField.get(t);
			result=db.delete(orm.getBeanTab(), keyColumn+"=?", new String[]{keyValue.toString()});
		}
		return result;
	}
}
