package com.rahul.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private Long iNumber;
	@Column
	private String name;
	@Column
	private String Address;	
	@Column 
	private int pincode;
	@Column
	private String Status;
	
	public OrderDetail() {
	}

	public OrderDetail(int id, Long iNumber, String name, String address, int pincode, String status) {
		super();
		this.id = id;
		this.iNumber = iNumber;
		this.name = name;
		this.Address = address;
		this.pincode = pincode;
		this.Status = status;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", iNumber=" + iNumber + ", name=" + name + ", Address=" + Address + ", pincode="
				+ pincode + ", Status=" + Status + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getiNumber() {
		return iNumber;
	}

	public void setiNumber(Long iNumber) {
		this.iNumber = iNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
		
}
