package com.arunava.entity;

public class Student {
	private int id;
	private String FirstName;
	private String lastName;
	private String email;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		FirstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Student(String firstName, String lastName, String email) {
		super();
		FirstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", FirstName=" + FirstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

}
