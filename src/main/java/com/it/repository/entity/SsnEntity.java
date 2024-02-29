package com.it.repository.entity;
/* Entity class == table in DB
 * classes involved in DB (DAO/DTO layer) should not be used in Rest API controller
 * To make them loosely coupled (decoupling), we need a service-layer
*/

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "social_security_number")
public class SsnEntity 
{
	private int sid;
	private String ssn;
	private String firstName;
	private String lastName;
	private Date dob;
	private String workAuth;
	private String notes;
	private Timestamp doe;
	
	@Id		// primary column
	@GeneratedValue(strategy = GenerationType.AUTO)		// auto-generation of ID
	public int getSid() {
		return sid;
	}
	
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	@Column(unique = true, length = 11)		//	make data in this column to be unique
	public String getSsn() {
		return ssn;
	}
	
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getWorkAuth() {
		return workAuth;
	}
	
	public void setWorkAuth(String workAuth) {
		this.workAuth = workAuth;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Timestamp getDoe() {
		return doe;
	}
	
	public void setDoe(Timestamp doe) {
		this.doe = doe;
	}



	
}
