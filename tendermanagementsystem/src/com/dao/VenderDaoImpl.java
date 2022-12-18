package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Vendor;
import com.utility.DBUtil;

public class VenderDaoImpl implements VenderDao {

	@Override
	public String registerVendor(Vendor vendor) {

		String status = "Registration Failed!!";

		try(Connection con = DBUtil.provideConnection()) {
			
			PreparedStatement pst = con.prepareStatement("select * from vendor where vemail=?");
		
			 pst.setString(1, vendor.getEmail());
			ResultSet rs = pst.executeQuery();
		
		if(rs.next()){
			
			status = "Registration Declined!<br>Email Id already Registered";
		}
		else{
		
			try {
			
				PreparedStatement ps = con.prepareStatement("insert into vendor values(?,?,?,?,?,?,?)");
				
				ps.setString(1, vendor.getId());
				ps.setString(2, vendor.getPassword());
				ps.setString(3, vendor.getName());
				ps.setString(4, vendor.getMobile());
				ps.setString(5, vendor.getEmail());
				ps.setString(6, vendor.getCompany());
				ps.setString(7, vendor.getAddress());
				
				int k = ps.executeUpdate();
				
				if(k>0) //update successful
					status = "Registration Successful.<br> Your Vendor id: "+vendor.getId()+"<br>Thanks For Registration";
			}
				
			catch(SQLException e){
				e.printStackTrace();
				status = "Error: "+e.getMessage();
			}
		}
	}
	catch(SQLException e){
		e.printStackTrace();
		status = "Error: "+ e.getErrorCode()+" : "+e.getMessage();
	}
	return status;
}

	@Override
	public List<Vendor> getAllVendors() {

		List<Vendor> vendorList = new ArrayList<Vendor>();

		try (Connection con = DBUtil.provideConnection()){
			PreparedStatement ps = con.prepareStatement("select * from vendor");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Vendor vendor = new Vendor(rs.getString("vid"),rs.getString("vname"), rs.getString("vmob"), rs.getString("vemail"), rs.getString("address"), rs.getString("company"), rs.getString("password"));
				vendorList.add(vendor);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vendorList;
	}

	@Override
	public boolean validatePassword(String vendorId, String password) {
		boolean flag=false;

		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement pst = conn.prepareStatement("select * from vendor where vid=? and password=?");
			
			pst.setString(1, vendorId);
			pst.setString(2, password);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
				flag = true;
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public String updateProfile(Vendor vendor) {
	String status = "Account Updation Failed";
		
		String vendorId = vendor.getId();
		String password = vendor.getPassword();
		
		VenderDao dao = new VenderDaoImpl();
		
		if(!dao.validatePassword(vendorId, password)){
			
			status = status + "<br>You Have Entered Wrong Password!";
			
			return status;
		}
		
		
		try(Connection con  = DBUtil.provideConnection()) {
			PreparedStatement ps = con.prepareStatement("update vendor set vname=?,vmob=?,vemail=?,company=?,address=? where vid=?");
			
			ps.setString(1,vendor.getName());
			ps.setString(2,vendor.getMobile());
			ps.setString(3, vendor.getEmail());
			ps.setString(4, vendor.getCompany());
			ps.setString(5, vendor.getAddress());
			ps.setString(6, vendor.getId());
			
			int x = ps.executeUpdate();
			
			if(x>0){
				status = "Account Updated Successfully!";
			}

			
		} catch (SQLException e) {
			
			status = "Error: "+e.getMessage();
			
			e.printStackTrace();
		}
	
		
		return status;
	}

	@Override
	public String changePassword(String vendorId, String oldPassword, String newPassword) {
		String status = "Password Updation failed!";
		
		
		VenderDao dao = new VenderDaoImpl();
		
		if(!dao.validatePassword(vendorId, oldPassword)){
			
			status = status + "<br>You Have Enetered Wrong Old Password!";
			
			return status;
		}

		try(Connection con = DBUtil.provideConnection()) {
			
			PreparedStatement ps = con.prepareStatement("update vendor set password = ? where vid=?");
			
			ps.setString(1, newPassword);
			ps.setString(2, vendorId);
			
			int x = ps.executeUpdate();
			
			if(x>0)
				status = "Password Updated Successfully!";
			
		} 
		catch (SQLException e) {
			
			status = status + "<br>Error: " +e.getMessage();
			
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public Vendor getVendorDataById(String vendorId) {
		Vendor vendor = null;

		try(Connection con = DBUtil.provideConnection()) {
			PreparedStatement ps = con.prepareStatement("select * from vendor where vid=?");
			
			ps.setString(1, vendorId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				vendor = new Vendor(rs.getString("vid"),rs.getString("vname"), rs.getString("vmob"), rs.getString("vemail"), rs.getString("address"), rs.getString("company"), rs.getString("password"));
			
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return vendor;
	}

}
