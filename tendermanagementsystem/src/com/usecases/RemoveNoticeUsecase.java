package com.usecases;

import java.util.Scanner;

import com.dao.NoticeDao;
import com.dao.NoticeDaoImpl;

public class RemoveNoticeUsecase {

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		System.out.println("Enter notice id to remove");
		int noticeId=sc.nextInt();
		NoticeDao dao=new NoticeDaoImpl();
		String result=dao.removeNotice(noticeId);
		System.out.println(result);
	}
}
