package com.tsm.model;

import java.sql.Date;

public class Tender {
	
	private String tid;
	private String tname;
	private String ttype;
	private int tprice;
	private String tdesc;
	private Date tdeadline;
	private String tlocation;
	
	public Tender() {
		
	}

	public Tender(String tid, String tname, String ttype, int tprice, String tdesc, Date tdeadline, String tlocation) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.ttype = ttype;
		this.tprice = tprice;
		this.tdesc = tdesc;
		this.tdeadline = tdeadline;
		this.tlocation = tlocation;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTtype() {
		return ttype;
	}

	public void setTtype(String ttype) {
		this.ttype = ttype;
	}

	public int getTprice() {
		return tprice;
	}

	public void setTprice(int tprice) {
		this.tprice = tprice;
	}

	public String getTdesc() {
		return tdesc;
	}

	public void setTdesc(String tdesc) {
		this.tdesc = tdesc;
	}

	public Date getTdeadline() {
		return tdeadline;
	}

	public void setTdeadline(Date tdeadline) {
		this.tdeadline = tdeadline;
	}

	public String getTlocation() {
		return tlocation;
	}

	public void setTlocation(String tlocation) {
		this.tlocation = tlocation;
	}

	@Override
	public String toString() {
		return "Tender [tid=" + tid + ", tname=" + tname + ", ttype=" + ttype + ", tprice=" + tprice + ", tdesc="
				+ tdesc + ", tdeadline=" + tdeadline + ", tlocation=" + tlocation + "]";
	}
	

}