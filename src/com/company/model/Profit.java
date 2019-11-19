package com.company.model;

public class Profit {
	
	private int count;
	private double price;

	
	public Profit(int count, double price) {
		super();
		this.count = count;
		this.price = price;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Profit ["+
			" всего " + count + 	
			" книг(и) на сумму" + price ;
			
	}
	
}
