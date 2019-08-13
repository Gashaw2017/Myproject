package com.geosolution.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@NamedQuery(name = "Store.findByName", query = "SELECT s FROM STORE s WHERE s.shopName = ?1")
@Entity(name="STORE")
public class Store {
	
	@Id
	@SequenceGenerator(name = "storedbsq", sequenceName = "storedbsqn", initialValue = 5, allocationSize = 99)
	@GeneratedValue(generator = "storedbsq")
	@Column(name="ID")
	private Integer id;
	@Column(name="SHOPNAME")
	private String shopName;
	@Column(name="CATEGORY")
	private String category;
	@Column(name="OWNERNAME")
	private String ownerName;
	@Column(name="LATITUDE")
	private String latitude;
	@Column(name="LONGITUDE")
	private String longitude;
	@Column(name="PHYSICALADDRESS")
	private String physicalAddress;
	
	public Store() {
	}
	
	public Store(String shopName, String category, String ownerName, String latitude, String longitude) {
		super();
		this.shopName = shopName;
		this.category = category;
		this.ownerName = ownerName;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getPhysicalAddress() {
		return physicalAddress;
	}
	
	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}
	
	
	

}
