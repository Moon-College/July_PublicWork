import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Test<T> {

	public static void main(String[] args) {
		Teacher t = new Teacher();
		t.setId(1);
		t.setName("ricky");
		Student[] students = new Student[3];
		int i = 1;
		List<Student> studentList = new ArrayList<Student>();
		for (Student student : students) {
			student = new Student();
			student.setId(i++);
			student.setName("jzhao" + i);
			student.setAge(i * i);
			studentList.add(student);
		}
		t.setStudents(studentList);
		try {
//			String stuStr= new Gson().toJson(t.getStudents());
//			System.out.println(stuStr);
			
			String myjsonStr =new Test().parseBeanToJson(t);
			System.out.println(myjsonStr);
			
			String mylistStr =new Test().parseListToJson(t.getStudents());
			System.out.println(mylistStr);
			
//			Teacher tt = new Gson().fromJson(myjsonStr, Teacher.class);
//			System.out.println(tt.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 将对象转换成JSON
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public String parseBeanToJson(T t) throws Exception{
		if(t==null)
			return "";
		String jsonStr="{";
		Class clazz = t.getClass();
		Field[] fields = clazz.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			// 循环所有定义的属性
			for (int i=0;i<fields.length;i++) {
				Field field =fields[i];
				// 判断属性类型
				String attrName=field.getName();
				jsonStr+="\""+attrName+"\":";
				//设置属性可以访问
				field.setAccessible(true);
				if (List.class.getName().equals(field.getType().getName())) {
//					jsonStr+="[";
//					//获取
//					List list=(List)field.get(t);
//					for(int j=0;j<list.size();j++){
//						String subJson=parseBeanToJson((T)list.get(j));
//						jsonStr+=subJson;
//						if(j<list.size()-1){
//							jsonStr+=",";
//						}
//					}
//					jsonStr+="]";
					jsonStr+=parseListToJson((T)field.get(t));
				} else if (String.class.getName().equals(field.getType().getName())) {
					String value=field.get(t).toString();
					jsonStr+="\""+value+"\"";
				} else if(int.class.getName().equals(field.getType().getName())){
					String value=field.get(t).toString();
					jsonStr+=value;
				}
				//如果是最后一个属性，不需要加逗号
				if(i<(fields.length-1)){
					jsonStr+=",";
				}
			}
		}
		jsonStr+="}";
		return jsonStr;
	}
	
	/**
	 * 将list转换成json
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public String parseListToJson(T t) throws Exception{
		if(t==null)
			return "";
		String jsonStr="[";
		List list=(List)t;
		for(int j=0;j<list.size();j++){
			String subJson=parseBeanToJson((T)list.get(j));
			jsonStr+=subJson;
			if(j<list.size()-1){
				jsonStr+=",";
			}
		}
		jsonStr+="]";
		return jsonStr;
	}
}
