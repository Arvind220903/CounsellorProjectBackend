package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="counsellor")
public class CounsellorEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer counsellorid;
	
	private String counsellorname;
	private String counselloremail;
	private String password;
	@OneToMany(mappedBy="counsellorid")
	private List<StudentEntity> students;
	public Integer getCounsellorid() {
		return counsellorid;
	}
	public void setCounsellorid(Integer counsellorid) {
		this.counsellorid = counsellorid;
	}
	public String getCounsellorname() {
		return counsellorname;
	}
	public void setCounsellorname(String counsellorname) {
		this.counsellorname = counsellorname;
	}
	public String getCounselloremail() {
		return counselloremail;
	}
	public void setCounselloremail(String counselloremail) {
		this.counselloremail = counselloremail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<StudentEntity> getStudents() {
		return students;
	}
	public void setStudents(List<StudentEntity> students) {
		this.students = students;
	}
	
	
	
}
