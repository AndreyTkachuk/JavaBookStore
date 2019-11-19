package com.company.model;

public class Order {
	
	private long id;
	private long customerId;
	private long employeeId;
	private long [] books;
	
	
	public Order(long id, long employeeId, long customerId, long[] books) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.employeeId = employeeId;
		this.books = books;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}


	public long getEmployeeId() {
		return employeeId;
	}


	public void setEmployee(long employeeId) {
		this.employeeId = employeeId;
	}


	public long[] getBooks() {
		return books;
	}


	public void setBooks(long[] books) {
		this.books = books;
	}

	

}
