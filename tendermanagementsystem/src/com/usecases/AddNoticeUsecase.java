package com.usecases;

import java.util.Scanner;

import com.dao.NoticeDao;
import com.dao.NoticeDaoImpl;

public class AddNoticeUsecase {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter notice title : ");
		String noticeTitle=sc.nextLine();
		System.out.println("enter notice description : ");
		String noticeDesc=sc.nextLine();
		
		NoticeDao dao=new NoticeDaoImpl();
	String result=dao.addNotice(noticeTitle, noticeDesc);
	System.out.println(result);
	
	}
}
