package com.usecases;

import java.util.Scanner;

import com.dao.NoticeDao;
import com.dao.NoticeDaoImpl;
import com.model.Notice;

public class UpdateNoticeUsecase {

	public static void main(String[] args) {
		
//		private int noticeId;
//		private String noticeTitle;
//		private String noticeInfo;
		Scanner sc=new Scanner(System.in);
		
		System.out.println("enter notice id which want to update");
		int noticeId=sc.nextInt();
		System.out.println("insert updated notice title");
		String noticeTitle=sc.next();
		System.out.println("insert updated notice information");
		String noticeInfo=sc.next();
		
		Notice notice=new Notice(noticeId, noticeTitle, noticeInfo);
		
		NoticeDao dao=new NoticeDaoImpl();
		String msg=dao.updateNotice(notice);
		System.out.println(msg);
		
		
	}
}
