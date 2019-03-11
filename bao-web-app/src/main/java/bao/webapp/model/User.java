package bao.webapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "users")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "type")
	private String type;

	@Column(name = "role")
	private String role;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "birthdate")
	private Date birthDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoll() {
		return role;
	}

	public void setRoll(String roll) {
		this.role = roll;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}