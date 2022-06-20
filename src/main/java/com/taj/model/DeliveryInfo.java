package com.taj.model;

public class DeliveryInfo {
	
	public String DId;
	
	public String fName;

	public String lName;

	public String emailId;

	public String phone;
	
	public String vehicleNo;
	
	public String vehicleName;

	public DeliveryInfo() {
		super();
	}

	public DeliveryInfo(String dId, String fName, String lName, String emailId, String phone, String vehicleNo,
			String vehicleName) {
		super();
		DId = dId;
		this.fName = fName;
		this.lName = lName;
		this.emailId = emailId;
		this.phone = phone;
		this.vehicleNo = vehicleNo;
		this.vehicleName = vehicleName;
	}

	public String getDId() {
		return DId;
	}

	public void setDId(String dId) {
		DId = dId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	
	

}
