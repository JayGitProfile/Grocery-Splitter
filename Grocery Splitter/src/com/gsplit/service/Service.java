package com.gsplit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.gsplit.model.ItemModel;
import com.gsplit.model.UserOwesModel;

public class Service {

	public static int nT;
	public static float total, tax, nonTaxTotal;
	public static Map<Integer, UserOwesModel> userMap;
	Scanner sc = new Scanner(System.in);
	
	public void userInit() {
		System.out.print("Enter no of users: ");
		nT = sc.nextInt();
		sc.nextLine();
		System.out.println();
		userMap = new HashMap<>();
		for(int i=1;i<=nT;i++) {
			System.out.print("User name "+i+": ");
			userMap.put(i, new UserOwesModel(sc.nextLine()));
		}
		System.out.println("__________________________________________");
	}
	
	public void billInit() {
		System.out.print("Enter total bill amount: ");
		total = sc.nextFloat(); 
		System.out.print("Enter tax %: ");
		tax = sc.nextFloat(); 
		nonTaxTotal = total/(1+(tax/100));
		
		userMap.entrySet().forEach(entry -> {
			UserOwesModel user = entry.getValue();
			float amount = nonTaxTotal/nT;
			user.setMoneyOwed(amount);
		});
		
		System.out.println("__________________________________________");
	}
	
	public void split() {
		while(true) {
			System.out.println("\n1) Add item to split\n2) Show money owed\n3) Final Bill");
			System.out.print("\nEnter option: ");
			int option = sc.nextInt();
			if(option>=1 && option<=3) {
				if(option==3) {
					break;
				}
				else if(option==2) {
					display(option);
				}
				else {
					ItemModel item = new ItemModel();
					System.out.print("Out of "+nT+" people, how many are excluded from "+item.getName()+"?: ");
					int n = sc.nextInt();
					while(n<=0 || n>=nT) {
						System.out.println("Incorrect value. Value should range from 1-"+(nT-1));
						System.out.print("Out of "+nT+" people, how many are excluded from "+item.getName()+"?: ");
						n = sc.nextInt();
					}
					userMap.entrySet().forEach(entry -> {
					    System.out.println(entry.getKey() + ") " + entry.getValue().getName());
					});
					int userCount=0;
					List<Integer> excludeIdList = new ArrayList<>();
					while(userCount!=n) {
						userCount++;
						System.out.print("\nChoose option to exclude, person"+userCount+": ");
						int excludeOption = sc.nextInt();
						while(excludeOption<1 || excludeOption>nT) {
							System.out.println("Incorrect value. Value should range from 1-"+nT);
							System.out.print("\nChoose option to exclude, person"+userCount+": ");
							excludeOption = sc.nextInt();
						}
						System.out.println(item.getName()+" will be excluded from "+userMap.get(excludeOption).getName()+"'s bill");
						excludeIdList.add(excludeOption);
					}
					excludeItem(item.price, excludeIdList);
				}
			}
		}
		System.out.println("__________________________________________");		
	}
	
	public void excludeItem(float price, List<Integer> excludeIdList) {
		int n = nT - excludeIdList.size();
		float each = price/nT;
		float tally = (price/n) - each;
		
		userMap.entrySet().forEach(entry -> {
			UserOwesModel user = entry.getValue();
			if(excludeIdList.contains(entry.getKey())) { //NI
				user.setMoneyOwed(user.getMoneyOwed()-each);
			}
			else {
				user.setMoneyOwed(user.getMoneyOwed()+tally);
			}
		});
		
	}
	
	public void finalBill() {
		userMap.entrySet().forEach(entry -> {
			UserOwesModel user = entry.getValue();
			user.setMoneyOwed(user.getMoneyOwed()*(1+(tax/100)));
		});
		
		display(3);
	}
	
	public void display(int option) {
		String op="BILL";
		if(option==2) {
			op += " (tax not included)";
		}
		System.out.println("\n\n~~~~~~~~~~~~~~~~~"+op+"~~~~~~~~~~~~~~~~~");
	
		userMap.entrySet().forEach(entry -> {
			UserOwesModel user = entry.getValue();
			System.out.println(entry.getKey() + ") " + user.getName()+": $"+user.getMoneyOwed());
		});
		System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
}
