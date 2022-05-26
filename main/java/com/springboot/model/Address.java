package com.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "address_id")
	private int addId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "street")
	private String addStreet;
	@Column(name = "pincode")
	private String addPincode;
	@Column(name = "city")
	private String addCity;
	@Column(name = "state")
	private String addState;

	@Transient
	private String addressId;

	public int getAddId() {
		return addId;
	}

	public void setAddId(int addId) {
		this.addId = addId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAddPincode() {
		return addPincode;
	}

	public void setAddPincode(String addPincode) {
		this.addPincode = addPincode;
	}

	public String getAddStreet() {
		return addStreet;
	}

	public void setAddStreet(String addStreet) {
		this.addStreet = addStreet;
	}

	public String getAddCity() {
		return addCity;
	}

	public void setAddCity(String addCity) {
		this.addCity = addCity;
	}

	public String getAddState() {
		return addState;
	}

	public void setAddState(String addState) {
		this.addState = addState;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(int addId, User user, String addPincode, String addStreet, String addLandmark, String addCity,
			String addState) {
		super();
		this.addId = addId;
		this.user = user;
		this.addPincode = addPincode;
		this.addStreet = addStreet;
		this.addCity = addCity;
		this.addState = addState;
	}

	@Override
	public String toString() {
		return "Address [addId=" + addId + ", addPincode=" + addPincode + ", addStreet=" + addStreet + ", addCity="
				+ addCity + ", addState=" + addState + "]";
	}

}
