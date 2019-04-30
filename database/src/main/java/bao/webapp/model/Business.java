package bao.webapp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Business")
public class Business {

	@Id
	@GeneratedValue
	private long BusinessID;

	@Column(name = "BusinessName")
	private String businessName;

	@Column(name = "CreateDate")
	private Date createDate;

	public long getBusinessID() {
		return BusinessID;
	}

	public void setBusinessID(Long BusinessID) {
		this.BusinessID = BusinessID;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@OneToMany(mappedBy = "business")
	private Set<Inventory> inventory;
}
