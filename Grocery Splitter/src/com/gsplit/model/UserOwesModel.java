package com.gsplit.model;

public class UserOwesModel {

	public String name;
	public float moneyOwed;
	
	public UserOwesModel() {}
	
	public UserOwesModel(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getMoneyOwed() {
		return moneyOwed;
	}
	public void setMoneyOwed(float moneyOwed) {
		this.moneyOwed = moneyOwed;
	}
	
	@Override
	public String toString() {
		return "UserOwesModel [name=" + name + ", moneyOwed=" + moneyOwed + "]";
	}
	
}
