package com.usecases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.model.Tender;

public class CreateTnderUsecase {

  
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
//		private String tid;
//		private String tname;
//		private String ttype;
//		private int tprice;
//		private String tdesc;
//		private Date tdeadline;
//		private String tlocation;
		
		System.out.println("Enter tender id");
		String tid=sc.next();
		System.out.println("Enter tender name");
		String tname=sc.next();
		System.out.println("Enter tender type");
		String ttype=sc.next();
		System.out.println("Enter tender price");
		int tprice=sc.nextInt();
		System.out.println("Enter tender description");
		String tdesc=sc.next();
		System.out.println("Enter tender deadline");
		String Date1=sc.next();
		try {
			java.util.Date tdeadline=new SimpleDateFormat("dd/MM/yyyy").parse(Date1);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Enter tender location");
		String tlocation=sc.next();
		
		Tender tender =new Tender(tid, tname, ttype, tprice, tdesc, tdeadline, tlocation)
	}
}
