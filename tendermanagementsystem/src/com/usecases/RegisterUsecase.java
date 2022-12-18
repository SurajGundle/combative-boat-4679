package com.usecases;

import java.util.Scanner;

import com.dao.VenderDao;
import com.dao.VenderDaoImpl;
import com.model.Vendor;

public class RegisterUsecase {
	public static void main(String[] args) {

		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("enter vendor id : ");
		String id=sc.next();
		System.out.println("enter vendor name : ");
		String name=sc.next();
		System.out.println("enter vendor mobile : ");
		String mobile=sc.next();
		System.out.println("enter vendor email : ");
		String email=sc.next();
		System.out.println("enter vendor address : ");
		String address=sc.next();
		System.out.println("enter vendor company : ");
		String company=sc.next();
		System.out.println("enter vendor password : ");
		String password=sc.next();
		
		Vendor vendor=new Vendor(id, name, mobile, email, address, company, password);
		VenderDao dao=new VenderDaoImpl();
		String result=dao.registerVendor(vendor);
		System.out.println(result);
	}

}
