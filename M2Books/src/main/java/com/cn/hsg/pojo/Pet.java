package com.cn.hsg.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class Pet {

	
	private String name; 
	private Calendar birthday; 
	private Pet friend;
	
	//监听对象属性
	private int fetchCount;
	
	public Pet() {}
	
	public Pet(String name) {
		this.name = name; 
	}
	/**
	 * 如果你的实体对象有一个静态的函数,
	 * 返回类型的是你的实体对象(有点类似于工厂方法),
	 * 输入参数是 java.sql.ResultSet, 
	 * 那么在创建实例的时候,会直接调用你这个方法。
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static Pet getInstance(ResultSet rs) throws SQLException{
		Pet pet = new Pet();
		pet.name = rs.getString("name");
		pet.birthday =(Calendar) rs.getObject("birthday");
		return pet;
		}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public Pet getFriend() {
		return friend;
	}

	public void setFriend(Pet friend) {
		this.friend = friend;
	}

	public int getFetchCount() {
		return fetchCount;
	}

	public void setFetchCount(int fetchCount) {
		this.fetchCount = fetchCount;
	}
	
	public void onFetch() { 
		this.fetchCount++;
	}
}
