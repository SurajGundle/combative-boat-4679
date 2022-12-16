package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Notice;
import com.utility.DBUtil;

public class NoticeDaoImpl implements NoticeDao {

	@Override
	public String addNotice(String noticeTitle, String noticeDesc) {

		String status = "Notice Addition Failed!";

		try(Connection con = DBUtil.provideConnection()) {
			
			PreparedStatement ps = con.prepareStatement("insert into notice(title,info) values(?,?)");
			
			ps.setString(1, noticeTitle);
			
			ps.setString(2, noticeDesc);
			
			int k = ps.executeUpdate();
			
			if(k>0){
				
				status = "Notice Added Successfully";
				
			}
			
		} catch (SQLException e) {

			status = "Error: " +e.getMessage();
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public String removeNotice(int noticeId) {

		String status = "Notice Deletion Failed";
		
		try(Connection con = DBUtil.provideConnection()) {
			
			PreparedStatement ps = con.prepareStatement("delete from notice where id=?");
			
			ps.setInt(1, noticeId);
			
			int x = ps.executeUpdate();
			
			if(x>0){
				status = "Notice No: "+noticeId+" has been Removed Successfully!";
			}
			
		} catch (SQLException e) {
			status = "Error: " +e.getMessage();
			
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public List<Notice> viewAllNotice() {

		List<Notice> noticeList = new ArrayList<Notice>();
		
		try(Connection con = DBUtil.provideConnection()){
			PreparedStatement ps = con.prepareStatement("select * from notice order by sysdate() asc limit 8");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				Notice notice = new Notice();
				
				notice.setNoticeId(rs.getInt("id"));
				
				notice.setNoticeTitle(rs.getString("title"));
				
				notice.setNoticeInfo(rs.getString("info"));
				
				noticeList.add(notice);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		return noticeList;
	}

	@Override
	public String updateNotice(Notice notice) {
String status = "Notice Updation Failed";

		try(Connection con = DBUtil.provideConnection()) {
			
			PreparedStatement ps = con.prepareStatement("update notice set title=? , info=? where id=?");
			
			ps.setString(1, notice.getNoticeTitle());
			
			ps.setString(2, notice.getNoticeInfo());
			
			ps.setInt(3, notice.getNoticeId());
			
			int x = ps.executeUpdate();
			
			if(x>0)
				status = "Notice Updated Successfully!";
			
		} catch (SQLException e) {
		
			status = "Error: "+e.getMessage();
			
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Notice getNoticeById(int noticeId) {
		Notice notice=null;
		try(Connection con = DBUtil.provideConnection()) {
			PreparedStatement ps = con.prepareStatement("select * from notice where id=?");
			
			ps.setInt(1, noticeId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				 notice = new Notice(noticeId,rs.getString("title"),rs.getString("info"));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return notice;
	}

}
