package com.mobile.mobile.entity;

import javax.persistence.*;

@Entity
@Table(name = "rent")
public class Rent {
	public Integer getRent_id() {
		return rent_id;
	}

	public void setRent_id(Integer rent_id) {
		this.rent_id = rent_id;
	}

	public String getRent_content() {
		return rent_content;
	}

	public void setRent_content(String rent_content) {
		this.rent_content = rent_content;
	}

	public String getRent_money() {
		return rent_money;
	}

	public void setRent_money(String rent_money) {
		this.rent_money = rent_money;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "rent_id")
	private Integer rent_id;

	@Column(name = "rent_content")
	private String rent_content;

	@Column(name = "rent_money")
	private String rent_money;
}
