package com.gsplit;

import com.gsplit.service.Service;

public class GrocerySplitter {
	
	public static void main(String[] args) {
		Service service = new Service();
		System.out.println("=============GROCERY SPLITTER=============\n");
		service.userInit();
		service.billInit();
		service.split();
		service.finalBill();
	}

}
