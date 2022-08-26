package com.chef.model;

public class ChefVO implements java.io.Serializable{
	private Integer chefid;
	private String chefAccount;
	private String chefPassword;
	private Integer chefStatus;
	private String chefName;
	private String chefNickname;
	private Integer chefPrice;
	private String schDate;
	private Integer reserve;
	private Integer com;
	private Integer gomg;
	private byte[] license;
	private byte[] idCard;
	private byte[] idCardBack;
	private byte[] chefPhoto;
	private String chefIntroduction;
	
	public ChefVO() {
		
	}
	
	public ChefVO(Integer chefid, String chefAccount, String chefPassword, Integer chefStatus, String chefName,
			String chefNickname, Integer chefPrice, String schDate, Integer reserve, Integer com, Integer gomg,
			byte[] license, byte[] idCard, byte[] idCardBack, byte[] chefPhoto, String chefIntroduction) {
		super();
		this.chefid = chefid;
		this.chefAccount = chefAccount;
		this.chefPassword = chefPassword;
		this.chefStatus = chefStatus;
		this.chefName = chefName;
		this.chefNickname = chefNickname;
		this.chefPrice = chefPrice;
		this.schDate = schDate;
		this.reserve = reserve;
		this.com = com;
		this.gomg = gomg;
		this.license = license;
		this.idCard = idCard;
		this.idCardBack = idCardBack;
		this.chefPhoto = chefPhoto;
		this.chefIntroduction = chefIntroduction;
	}
	
	public Integer getChefid() {
		return chefid;
	}
	public void setChefid(Integer chefid) {
		this.chefid = chefid;
	}
	public String getChefAccount() {
		return chefAccount;
	}
	public void setChefAccount(String chefAccount) {
		this.chefAccount = chefAccount;
	}
	public String getChefPassword() {
		return chefPassword;
	}
	public void setChefPassword(String chefPassword) {
		this.chefPassword = chefPassword;
	}
	public Integer getChefStatus() {
		return chefStatus;
	}
	public void setChefStatus(Integer chefStatus) {
		this.chefStatus = chefStatus;
	}
	public String getChefName() {
		return chefName;
	}
	public void setChefName(String chefName) {
		this.chefName = chefName;
	}
	public String getChefNickname() {
		return chefNickname;
	}
	public void setChefNickname(String chefNickname) {
		this.chefNickname = chefNickname;
	}
	public Integer getChefPrice() {
		return chefPrice;
	}
	public void setChefPrice(Integer chefPrice) {
		this.chefPrice = chefPrice;
	}
	public String getSchDate() {
		return schDate;
	}
	public void setSchDate(String schDate) {
		this.schDate = schDate;
	}
	public Integer getReserve() {
		return reserve;
	}
	public void setReserve(Integer reserve) {
		this.reserve = reserve;
	}
	public Integer getCom() {
		return com;
	}
	public void setCom(Integer com) {
		this.com = com;
	}
	public Integer getGomg() {
		return gomg;
	}
	public void setGomg(Integer gomg) {
		this.gomg = gomg;
	}
	public byte[] getLicense() {
		return license;
	}
	public void setLicense(byte[] license) {
		this.license = license;
	}
	public byte[] getIdCard() {
		return idCard;
	}
	public void setIdCard(byte[] idCard) {
		this.idCard = idCard;
	}
	public byte[] getIdCardBack() {
		return idCardBack;
	}
	public void setIdCardBack(byte[] idCardBack) {
		this.idCardBack = idCardBack;
	}
	public byte[] getChefPhoto() {
		return chefPhoto;
	}
	public void setChefPhoto(byte[] chefPhoto) {
		this.chefPhoto = chefPhoto;
	}
	public String getChefIntroduction() {
		return chefIntroduction;
	}
	public void setChefIntroduction(String chefIntroduction) {
		this.chefIntroduction = chefIntroduction;
	}
	
	

}
