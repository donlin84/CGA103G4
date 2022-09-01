package com.emp.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.announcement.model.AnnouncementVO;

public class EmpVO implements java.io.Serializable {
	private Integer empid;
	private String empName;
	private String empPhone;
	private byte[] empPicture;
	private String empAccount;
	private String empPassword;
	private Integer empLevel;
	private Integer empStatus;
	private Date empHiredate;
	private Set<AnnouncementVO> annoucements = new HashSet<AnnouncementVO>();

	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public byte[] getEmpPicture() {
		return empPicture;
	}

	public void setEmpPicture(byte[] empPicture) {
		this.empPicture = empPicture;
	}

	public String getEmpAccount() {
		return empAccount;
	}

	public void setEmpAccount(String empAccount) {
		this.empAccount = empAccount;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public Integer getEmpLevel() {
		return empLevel;
	}

	public void setEmpLevel(Integer empLevel) {
		this.empLevel = empLevel;
	}

	public Integer getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(Integer empStatus) {
		this.empStatus = empStatus;
	}

	public Date getEmpHiredate() {
		return empHiredate;
	}

	public void setEmpHiredate(Date empHiredate) {
		this.empHiredate = empHiredate;
	}

	public Set<AnnouncementVO> getAnnoucements() {
		return annoucements;
	}

	public void setAnnoucements(Set<AnnouncementVO> annoucements) {
		this.annoucements = annoucements;
	}
	

}
