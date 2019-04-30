package bao.webapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name = "Orders")
public class Order {
	
	@Id
	@GeneratedValue
	private long OrderID;
	
	@Column(name = "Status")
	private String status;
	
	@Column(name = "CreateDate")
	private Date createDate;
	
	@Column(name = "Destination")
	private String destination;
	
	@Column(name = "UserID")
	private long userID;
	
	@Column(name = "BusinessID")
	private long businessID;
	
	@Column(name = "Goods")
	private String goods;
	
	public long getOrderID() {
		return OrderID;
	}
	
	public void setOrderID(Long orderID) {
		this.OrderID = orderID;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public long getUserID() {
		return userID;
	}
	
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	
	public long getBusinessID() {
		return businessID;
	}
	
	public void setBusinessID(Long businessID) {
		this.businessID = businessID;
	}
	
	public String getGoods() {
		return goods;
	}
	
	public void setGoods(String goods) {
		this.goods = goods;
	}
}
