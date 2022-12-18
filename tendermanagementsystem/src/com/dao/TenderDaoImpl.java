package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Tender;
import com.model.TenderStatus;
import com.utility.DBUtil;

public class TenderDaoImpl implements TenderDao {

	@Override
	public List<Tender> getTenderDetails(String tid) {
List<Tender> tenderList = new ArrayList<Tender>();

		try(Connection con = DBUtil.provideConnection()){
			
			PreparedStatement ps = con.prepareStatement("select * from tender where tid=?");
			
			ps.setString(1, tid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				Tender tender = new Tender();
			String id =  	rs.getString(1);
			String name=	rs.getString(2);
			String type=	rs.getString(3);
			int price   =	    rs.getInt(4);
			String desc=	rs.getString(5);
			String deadline=rs.getString(6);
			String location=rs.getString(7);
			
			 tender=new Tender(id, name, type, price, desc, null, location);
			tenderList.add(tender);
			}
			else{
				PreparedStatement pst = con.prepareStatement("select * from tender where tname like '%"+tid+"%'");
				
				ResultSet rss = pst.executeQuery();
				
				while(rss.next()){
					Tender tender = new Tender();
					String id =  	rss.getString(1);
					String name=	rss.getString(2);
					String type=	rss.getString(3);
					int price   =	rss.getInt(4);
					String desc=	rss.getString(5);
					String deadline=rss.getString(6);
					String location=rss.getString(7);
					tender=new Tender(tid, name, type, price, desc, null, location);
					tenderList.add(tender);
				}
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tenderList;
	}

	@Override
	public List<Tender> getAllTenders() {
List<Tender> tenderList  = new ArrayList<Tender>();
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("select * from tender");
			
			rs=ps.executeQuery();
			while(rs.next())
			{
				Tender tender=new Tender();
				tender.setTid(rs.getString("tid"));
				tender.setTdeadline(null);
				tender.setTname (rs.getString("tname"));
				tender.setTtype(rs.getString("ttype"));
				tender.setTprice(rs.getInt("tprice"));
				tender.setTdesc(rs.getString("tdesc"));
				java.util.Date udate = new java.util.Date(rs.getDate(6).getTime());
				tender.setTdeadline(udate);
				tender.setTlocation(rs.getString("tloc"));
				tenderList.add(tender);
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	
		
		return tenderList;
	}

	@Override
	public String createTender(Tender tender) {

		String status = "Tender Addition Failed!";

		try(Connection conn=DBUtil.provideConnection()) {
			PreparedStatement pst= conn.prepareStatement("insert into tender values(?,?,?,?,?,?,?)");
			pst.setString(1, tender.getTid());
			pst.setString(2, tender.getTname());
			pst.setString(3, tender.getTdesc());
			pst.setInt(4, tender.getTprice());
			pst.setString(5, tender.getTdesc());
			
			Date deadline = tender.getTdeadline();
			java.sql.Date sDeadline = new java.sql.Date(deadline.getTime());
			pst.setDate(6, sDeadline);
			
			pst.setString(7, tender.getTlocation());
			
			int x=pst.executeUpdate();
			if(x>0)
				status = "New Tender Inserted<br> Your Tender id: "+tender.getTid();
		
		} catch (SQLException e) {
			
			status="Error : "+e.getMessage();
			
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public boolean removeTender(String tid) {

		boolean flag=false;

		try(Connection con = DBUtil.provideConnection()) {
			
			PreparedStatement ps = con.prepareStatement("delete from tender where tid=?");
			
			ps.setString(1, tid);
			
			int x= ps.executeUpdate();
			
			if(x>0){
				
			flag=true;
			
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public String updateTender(Tender tender) {
		String status = "Tender Updation Failed!";
		try(Connection con = DBUtil.provideConnection()) {
			PreparedStatement pst = con.prepareStatement("UPDATE tender SET tname=?,ttype=?,tprice=?,tdesc=?,tdesc=?,tloc=? where tid=?");
			
			pst.setString(1, tender.getTname());
			pst.setString(2, tender.getTdesc());
			pst.setInt(3, tender.getTprice());
			pst.setString(4, tender.getTdesc());
			
			Date deadline = tender.getTdeadline();
			java.sql.Date sDeadline = new java.sql.Date(deadline.getTime());
			pst.setDate(5, sDeadline);
			
			pst.setString(6, tender.getTlocation());
			pst.setString(7, tender.getTid());
			int x=pst.executeUpdate();
			if(x>0)
				status="TENDER DETAILS UPDATED SUCCSESFULLY";
			
		} catch (SQLException e) {
			status = "Error: "+e.getMessage();
			e.printStackTrace();
		}
	
		return status;
	}

	@Override
	public Tender getTenderDataById(String tenderId) {
	
		Tender tender = null;
		
		PreparedStatement pst = null;
		try (Connection con = DBUtil.provideConnection()){
			
			PreparedStatement ps = con.prepareStatement("select * from tender where tid=?");
			
			ps.setString(1, tenderId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
			String id =  	rs.getString(1);
			String name=	rs.getString(2);
			String type=	rs.getString(3);
			int price   =	    rs.getInt(4);
			String desc=	rs.getString(5);
			String deadline=rs.getString(6);
			String location=rs.getString(7);
			
			tender =new Tender(id, name, type, price, desc, null, location);
		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tender;
	}

	@Override
	public String getTenderStatus(String tenderId) {

		String status = "Not Assigned";

		try(Connection con = DBUtil.provideConnection()) {
			PreparedStatement ps = con.prepareStatement("select * from tenderstatus where tid=?");
			
			ps.setString(1, tenderId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				//Tender Has been Assigned 
				
				status = "Assigned";
			}
			
		} catch (SQLException e) {
			status = "Error: "+e.getMessage();
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String assignTender(String tenderId, String vendorId, String bidderId) {
	String status = "Tender Assigning failed";

		try(Connection con = DBUtil.provideConnection()) {
			PreparedStatement ps = con.prepareStatement("select * from tenderstatus where tid=?");
			ps.setString(1, tenderId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				
				status = "Tender is Already Assigned to Vendor: "+rs.getString("vid");
			}
			else{
				
				ps = con.prepareStatement("insert into tenderstatus values(?,?,?,?)");
				ps.setString(1,tenderId);
				ps.setString(2, bidderId);
				ps.setString(3, "Assigned");
				ps.setString(4, vendorId);

				int k = ps.executeUpdate();
				if(k>0){
					status = "Tender: "+tenderId+" has been Assigned to vendor: "+vendorId;
				}
				
			}
		} catch (SQLException e) {
			status = status + e.getMessage();
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public List<TenderStatus> getAllAssignedTenders() {

		List<TenderStatus> statusList = new ArrayList<TenderStatus>();

		try(Connection con = DBUtil.provideConnection()) {
		
			PreparedStatement ps = con.prepareStatement("select * from tenderstatus");
		
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				TenderStatus status = new TenderStatus(rs.getString("tid"),rs.getString("bid"),rs.getString("status"),rs.getString("vid"));
				
				statusList.add(status);
			}
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return statusList;
	}

}
