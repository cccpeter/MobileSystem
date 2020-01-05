package com.mobile.mobile.entity;

import javax.persistence.*;

@Entity
@Table(name = "packagelist")
public class Packagelist {

	public Integer getPackage_id() {
		return package_id;
	}

	public void setPackage_id(Integer package_id) {
		this.package_id = package_id;
	}

	public String getPackage_monthRent() {
		return package_monthRent;
	}

	public void setPackage_monthRent(String package_monthRent) {
		this.package_monthRent = package_monthRent;
	}

	public String getPackage_minCharge() {
		return package_minCharge;
	}

	public void setPackage_minCharge(String package_minCharge) {
		this.package_minCharge = package_minCharge;
	}

	public String getPackage_msgCharge() {
		return package_msgCharge;
	}

	public void setPackage_msgCharge(String package_msgCharge) {
		this.package_msgCharge = package_msgCharge;
	}

	public String getPackage_totalTraffic() {
		return package_totalTraffic;
	}

	public void setPackage_totalTraffic(String package_totalTraffic) {
		this.package_totalTraffic = package_totalTraffic;
	}
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "package_id")
	private Integer package_id;

	@Column(name = "package_monthRent")
	private String package_monthRent;

	@Column(name = "package_minCharge")
	private String package_minCharge;

	@Column(name = "package_msgCharge")
	private String package_msgCharge;

	@Column(name = "package_totalTraffic")
	private String package_totalTraffic;
}
