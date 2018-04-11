package com.messenger.common;

public class MemberDTO {
	
	private String id,passwd,name,alias,loc,sex,age,birth,phone;

	public MemberDTO(String id, String passwd, String name, String loc, String sex, String age,
			String birth, String phone) {
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.loc = loc;
		this.sex = sex;
		this.age = age;
		this.birth = birth;
		this.phone = phone;
	}
	public MemberDTO(String id, String passwd, String name, String alias, String loc, String sex, String age,
			String birth, String phone) {
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.alias = alias;
		this.loc = loc;
		this.sex = sex;
		this.age = age;
		this.birth = birth;
		this.phone = phone;
	}
	public MemberDTO() {
	}
	public String getId() {
		return id;
	}
	public String setId(String id) {
		return this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public String setPasswd(String passwd) {
		return this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[MemberDTO]")
		  .append("id:"+id+"\r\n")
		  .append("passwd:"+passwd+"\r\n")
		  .append("name:"+name+"\r\n")
		  .append("alias:"+alias+"\r\n")
		  .append("loc:"+loc+"\r\n")
		  .append("sex:"+sex+"\r\n")
		  .append("age:"+age+"\r\n")
		  .append("birth:"+birth+"\r\n")
		  .append("phone:"+phone+"\r\n");
//		System.out.println(
//				sb
//		);
		return sb.toString();
	}
	
	
	
}
