package com.javatpoint.beans;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 作成者 : エエティン
 * クラス名 : Emp 
 * 概要 　　: 社員情報モデル
 * 作成日　:　2021/08/02
 */

public class Emp {
	private int id;
	private String name;
	private int gender;
	private int age;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthday;
	private String tel;
	private String address;
	private String category;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date joined_day;
	private String password;
	private int admin_auth;

	private float salary;
	private String designation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getJoined_day() {
		return joined_day;
	}

	public void setJoined_day(Date joined_day) {
		this.joined_day = joined_day;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAdmin_auth() {
		return admin_auth;
	}

	public void setAdmin_auth(int admin_auth) {
		this.admin_auth = admin_auth;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}