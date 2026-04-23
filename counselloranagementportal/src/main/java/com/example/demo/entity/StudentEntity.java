package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="student")
public class StudentEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer studentid;
	private String studentemail;
	private String studnentcontact;
	private String course;
	private String status;
	
	private Integer counsellorid;
	public Integer getStudentid() {
		return studentid;
	}
	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}
	public String getStudentemail() {
		return studentemail;
	}
	public void setStudentemail(String studentemail) {
		this.studentemail = studentemail;
	}
	public String getStudnentcontact() {
		return studnentcontact;
	}
	public void setStudnentcontact(String studnentcontact) {
		this.studnentcontact = studnentcontact;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCounsellorid() {
		return counsellorid;
	}
	public void setCounsellorid(Integer counsellorid) {
		this.counsellorid = counsellorid;
	}
	
	
	
	
}
