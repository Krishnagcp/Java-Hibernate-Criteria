package com.model;


import javax.persistence.*;



@Entity
@Table(name="EmpDetails")

public class Employee {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emp_id")
	private int id;
	@Column(name="emp_name")
	private String name;
	@Column(name="emp_salary")
	private float salary;
	public Employee() {
		super();
	}
	public Employee(int id, String name, float salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	


}
