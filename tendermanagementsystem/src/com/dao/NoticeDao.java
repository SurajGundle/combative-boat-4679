package com.dao;

import java.util.List;

import com.model.Notice;

public interface NoticeDao {

	 public String addNotice(String noticeTitle, String noticeDesc );
		
	 public String removeNotice(int noticeId);
	
	 public List<Notice> viewAllNotice();
	
	 public String updateNotice(Notice notice);
	
	 public Notice getNoticeById(int noticeId);
}
