package com.example.demo.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employee")
public class EmpData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long emp_id;
	
	private String emp_name;
	
	
	private Integer emp_age;
    @DateTimeFormat(pattern = "dd-MM-yyyy")

	private LocalDate emp_date;
	public Long getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public Integer getEmp_age() {
		return emp_age;
	}
	public void setEmp_age(Integer emp_age) {
		this.emp_age = emp_age;
	}
	public LocalDate getEmp_date() {
		return emp_date;
	}
	public void setEmp_date(LocalDate emp_date) {
		this.emp_date = emp_date;
	}
	
	
	

}
