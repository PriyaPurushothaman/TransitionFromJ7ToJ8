package com;

import java.math.BigDecimal;

public class Developer {

	
	public String name;

	public int age;

	public BigDecimal salary;

public Developer(String name, BigDecimal salary, int age) {
	this.name 	= name;
	this.age 	= age;
	this.salary = salary;

}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public BigDecimal getSalary() {
	return salary;
}
public void setSalary(BigDecimal salary) {
	this.salary = salary;
}

}
