package com.dao;

import java.util.List;

import com.model.Bidder;

public interface BidderDao {
	 
	    public String acceptBid(String applicationId,String tenderId,String vendorId);
		
		public String rejectBid(String applicationId);
		
		public String bidTender(String tenderId, String vendorId,String bidAmount,String deadline);
		
		public List<Bidder> getAllBidsOfaTender(String tenderId);
		
		public List<Bidder> getAllBidsOfaVendor(String vendorId);

}
