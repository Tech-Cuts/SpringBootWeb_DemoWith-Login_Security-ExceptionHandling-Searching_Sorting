package com.it.repository.entity;
/* Entity class == table in DB
 * classes involved in DB (DAO/DTO layer) should not be used in Rest API controller
 * To make them loosely coupled (decoupling), we need a service-layer
*/

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="signup_tbl")
public class Signup 
{
	@Id		// primary column
	private String username;		
	// additional columns
	private String password;		
	private String email;
	private String gender;
	private Date timestamp;
	@Column(columnDefinition="varchar(30) default 'CUSTOMER'")
	private String role;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}