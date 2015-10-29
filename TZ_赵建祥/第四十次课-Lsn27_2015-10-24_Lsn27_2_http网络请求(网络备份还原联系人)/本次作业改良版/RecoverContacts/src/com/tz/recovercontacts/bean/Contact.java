package com.tz.recovercontacts.bean;

public class Contact {

	private Integer id;
	private String name;
	private String email;
	private String tel;
	private String phone;
	
	public Contact(){
		
	}
	
	public Contact(Integer id, String name, String email, String tel, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.phone = phone;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", email=" + email
				+ ", tel=" + tel + ", phone=" + phone + "]";
	}
	
}