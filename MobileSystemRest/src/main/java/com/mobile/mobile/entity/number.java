package com.mobile.mobile.entity;

import javax.persistence.*;

@Entity
public class number {

	public Integer getNumber_id() {
		return number_id;
	}

	public void setNumber_id(Integer number_id) {
		this.number_id = number_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getNumber_number() {
		return number_number;
	}

	public void setNumber_number(String number_number) {
		this.number_number = number_number;
	}

	public String getNumber_status() {
		return number_status;
	}

	public void setNumber_status(String number_status) {
		this.number_status = number_status;
	}

	public String getPackagelist_id_list() {
		return packagelist_id_list;
	}

	public void setPackagelist_id_list(String packagelist_id_list) {
		this.packagelist_id_list = packagelist_id_list;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "number_id")
	private Integer number_id;

	@Column(name = "user_id")
	private int user_id;

	@Column(name = "number_number")
	private String number_number;

	@Column(name = "number_status")
	private String number_status;

	@Column(name = "packagelist_id_list")
	private String packagelist_id_list;
}
