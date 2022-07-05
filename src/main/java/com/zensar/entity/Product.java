package com.zensar.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.procedure.internal.Util.ResultClassesResolutionContext;


//@NamedQueries(value = { @NamedQuery(name = "Product.test", query = "From Product p where p.productName=?1")})
//@NamedNativeQuery(name = "Product.test3", query ="From Product p where p.productName=?1 and  p.productCost=?2", resultClass=Product.class)
		
@Entity
public class Product {
	@Id
	private int productId;
	private String productName;
	private int productCost;

	public Product() {
		super();
	}

	public Product(int productId, String productName, int productCost) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCost = productCost;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductCost() {
		return productCost;
	}

	public void setProductCost(int productCost) {
		this.productCost = productCost;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productCost=" + productCost
				+ "]";
	}

}
