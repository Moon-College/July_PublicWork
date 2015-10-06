package com.tz.orm.dao;

import com.tz.orm.bean.Product;
import com.tz.orm.util.MySqliteOpenHelper;

public class ProductDao extends BaseDao<Product>{

	public ProductDao(MySqliteOpenHelper db) {
		super(db,Product.class);
	}
	
}
