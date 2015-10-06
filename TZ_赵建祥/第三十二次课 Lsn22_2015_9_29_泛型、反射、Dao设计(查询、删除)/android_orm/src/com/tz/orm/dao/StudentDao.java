package com.tz.orm.dao;

import com.tz.orm.bean.Student;
import com.tz.orm.util.MySqliteOpenHelper;

public class StudentDao extends BaseDao<Student>{

	public StudentDao(MySqliteOpenHelper db) {
		super(db,Student.class);
	}
	
}
