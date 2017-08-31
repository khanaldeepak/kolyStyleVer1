package com.kolystyle.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PromoCodes {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String couponCode;
	private Date startDate;
	private Date expiryDate;
	private String PercentOrDollar;
	private BigDecimal cartTotal;
	private int cartItemQty;
	private int promoUseCount;
	
	//Either Specific User or All
	private String promoType;
	private int promoValue;
	
	private boolean promoStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getPercentOrDollar() {
		return PercentOrDollar;
	}

	public void setPercentOrDollar(String percentOrDollar) {
		PercentOrDollar = percentOrDollar;
	}

	public BigDecimal getCartTotal() {
		return cartTotal;
	}

	public void setCartTotal(BigDecimal cartTotal) {
		this.cartTotal = cartTotal;
	}

	public int getCartItemQty() {
		return cartItemQty;
	}

	public void setCartItemQty(int cartItemQty) {
		this.cartItemQty = cartItemQty;
	}

	public int getPromoUseCount() {
		return promoUseCount;
	}

	public void setPromoUseCount(int promoUseCount) {
		this.promoUseCount = promoUseCount;
	}

	public String getPromoType() {
		return promoType;
	}

	public void setPromoType(String promoType) {
		this.promoType = promoType;
	}

	public int getPromoValue() {
		return promoValue;
	}

	public void setPromoValue(int promoValue) {
		this.promoValue = promoValue;
	}

	public boolean isPromoStatus() {
		return promoStatus;
	}

	public void setPromoStatus(boolean promoStatus) {
		this.promoStatus = promoStatus;
	}
	
	
}
