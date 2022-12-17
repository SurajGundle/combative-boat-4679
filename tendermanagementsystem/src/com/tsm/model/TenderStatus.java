package com.tsm.model;

public class TenderStatus {
	
	private String tstendorId;
	private String tsbidderId;
	private String tsstatus;
	private String tsvendorId;
	
	public TenderStatus() {
		
	}

	public TenderStatus(String tstendorId, String tsbidderId, String tsstatus, String tsvendorId) {
		super();
		this.tstendorId = tstendorId;
		this.tsbidderId = tsbidderId;
		this.tsstatus = tsstatus;
		this.tsvendorId = tsvendorId;
	}

	public String getTstendorId() {
		return tstendorId;
	}

	public void setTstendorId(String tstendorId) {
		this.tstendorId = tstendorId;
	}

	public String getTsbidderId() {
		return tsbidderId;
	}

	public void setTsbidderId(String tsbidderId) {
		this.tsbidderId = tsbidderId;
	}

	public String getTsstatus() {
		return tsstatus;
	}

	public void setTsstatus(String tsstatus) {
		this.tsstatus = tsstatus;
	}

	public String getTsvendorId() {
		return tsvendorId;
	}

	public void setTsvendorId(String tsvendorId) {
		this.tsvendorId = tsvendorId;
	}

	@Override
	public String toString() {
		return "TenderStatus [tstendorId=" + tstendorId + ", tsbidderId=" + tsbidderId + ", tsstatus=" + tsstatus
				+ ", tsvendorId=" + tsvendorId + "]";
	}
	
	

}