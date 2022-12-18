package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Bidder;
import com.utility.DBUtil;
import com.utility.IDUtil;

public class BidderDaoImpl implements BidderDao {

	@Override
	public String acceptBid(String applicationId, String tenderId, String vendorId) {
         
		String msg = "Bid Acceptance Failed";
	
		try (Connection con = DBUtil.provideConnection();){
			
			PreparedStatement ps = con.prepareStatement("select * from tenderstatus where tid=?");
			ps.setString(1, tenderId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				msg = "Project Already Assigned";
			}
			else{
			
				PreparedStatement pst = con.prepareStatement("update bidder set status = ? where bid=? and status=?");
				
				pst.setString(1, "Accepted");
				pst.setString(2, applicationId);
				pst.setString(3, "Pending");
				
				int x = pst.executeUpdate();
				if(x>0){
					msg = "Bid Has Been Accepted Successfully!";
					TenderDao dao = new TenderDaoImpl();
					msg = msg + "<br>"+dao.assignTender(tenderId, vendorId,applicationId);
				}
			}
		} catch (SQLException e) {

			msg = msg + "Error: "+e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public String rejectBid(String applicationId) {
		
        String msg = "Bid Rejection Failed";
		
		try (Connection con = DBUtil.provideConnection();){
			PreparedStatement ps = con.prepareStatement("update bidder set status = ? where bid=? and status = ?");
			
			ps.setString(1, "Rejected");
			ps.setString(2, applicationId);
			ps.setString(3, "Pending");
			
			int x = ps.executeUpdate();
			if(x>0)
				msg = "Bid Has Been Rejected Successfully!";
			
		} catch (SQLException e) {

			msg = msg + "Error: "+e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public String bidTender(String tenderId, String vendorId, String bidAmount, String deadline) {
        
		String status = "Tender Bidding Failed!";
		
		String bidId = IDUtil.generateBidderId();
		
		
		String bidStatus = "Pending";
		
//		Bidder bidder = new Bidder(bidId, vendorId, tenderId, bidAmount, deadline, bidStatus);
		
		Bidder bidder=new Bidder(bidId, vendorId, tenderId, 0, null, bidStatus);
		
		try (Connection con = DBUtil.provideConnection()){
			PreparedStatement ps = con.prepareStatement("insert into bidder values(?,?,?,?,?,?)");
			
			ps.setString(1, bidId);
			ps.setString(2, vendorId);
			ps.setString(3,tenderId);
			ps.setInt(4, bidder.getBidAmount());
			
			java.sql.Date bidDate = new java.sql.Date(bidder.getBidDeadline().getTime());
			
			ps.setDate(5, bidDate);
			ps.setString(6, bidStatus);
			
			int x = ps.executeUpdate();
			
			if(x>0)
				status = "You have successfully Bid for the tender";
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public List<Bidder> getAllBidsOfaTender(String tenderId) {
List<Bidder> bidderList = new ArrayList<Bidder>();

		try(Connection con = DBUtil.provideConnection()) {
			
			PreparedStatement ps = con.prepareStatement("select * from bidder where tid=?");
			
			ps.setString(1, tenderId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Bidder bidder = new Bidder();
				
				bidder.setBidAmount(rs.getInt("bidamount"));
				bidder.setBidDeadline(new java.sql.Date(rs.getDate("deadline").getTime()));
				bidder.setBidId(rs.getString("bid"));
				bidder.setBidStatus(rs.getString("status"));
				bidder.setTenderId(rs.getString("tid"));
				bidder.setVendorId(rs.getString("vid"));
				
				bidderList.add(bidder);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bidderList;
	}

	@Override
	public List<Bidder> getAllBidsOfaVendor(String vendorId) {
List<Bidder> bidderList = new ArrayList<Bidder>();

		try(Connection con = DBUtil.provideConnection()) {
			
			PreparedStatement ps = con.prepareStatement("select * from bidder where vid=?");
			
			ps.setString(1, vendorId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Bidder bidder = new Bidder();
				
				bidder.setBidAmount(rs.getInt("bidamount"));
				bidder.setBidDeadline(new java.sql.Date(rs.getDate("deadline").getTime()));
				bidder.setBidId(rs.getString("bid"));
				bidder.setBidStatus(rs.getString("status"));
				bidder.setTenderId(rs.getString("tid"));
				bidder.setVendorId(rs.getString("vid"));
				
				bidderList.add(bidder);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return bidderList;
	}

}
