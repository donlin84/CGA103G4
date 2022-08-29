package com.ClassTag.model;

import java.io.Serializable;

public class ClassTagVO implements Serializable{
	private Integer claTagid;
	private String claTagName;
	private Integer claTagStatus;
	
	
	public Integer getClaTagid() {
		return claTagid;
	}
	public void setClaTagid(Integer claTagid) {
		this.claTagid = claTagid;
	}
	public String getClaTagName() {
		return claTagName;
	}
	public void setClaTagName(String claTagName) {
		this.claTagName = claTagName;
	}
	public Integer getClaTagStatus() {
		return claTagStatus;
	}
	public void setClaTagStatus(Integer claTagStatus) {
		this.claTagStatus = claTagStatus;
	}	
	
	
}
