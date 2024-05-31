package com.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Products")
public class Product {

	@Id
	private int productid;
	private String productname;
	private int productPrice;
	private String productCompany;
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductCompany() {
		return productCompany;
	}
	public void setProductCompany(String productCompany) {
		this.productCompany = productCompany;
	}
	public Product(String productname, int productPrice, String productCompany) {
		super();
		this.productname = productname;
		this.productPrice = productPrice;
		this.productCompany = productCompany;
	}
	public Product() {
		super();
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public Product(int productid) {
		super();
		this.productid = productid;
	}
	
	
}
