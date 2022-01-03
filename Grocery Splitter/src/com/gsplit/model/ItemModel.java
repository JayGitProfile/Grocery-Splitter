package com.gsplit.model;

import java.util.Scanner;

public class ItemModel {

	public static int id;
	public String name;
	public float price;
	
	Scanner sc = new Scanner(System.in);
	
	public ItemModel() {
		this.id += 1; 
		System.out.print("\nEnter item name: ");
		this.name = sc.nextLine();
		System.out.print("Enter "+name+" price: ");
		this.price = sc.nextFloat();
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		ItemModel.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ItemModel [id=" +id+", name=" + name + ", price=" + price + "]";
	}
	
	
	
}
