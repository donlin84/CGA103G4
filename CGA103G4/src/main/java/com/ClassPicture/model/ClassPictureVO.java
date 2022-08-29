package com.ClassPicture.model;

import java.io.Serializable;

public class ClassPictureVO implements Serializable {
	private Integer claPicid;
	private Integer claid;
	private byte[] claPic;
	
	
	public Integer getClaPicid() {
		return claPicid;
	}
	public void setClaPicid(Integer claPicid) {
		this.claPicid = claPicid;
	}
	public Integer getClaid() {
		return claid;
	}
	public void setClaid(Integer claid) {
		this.claid = claid;
	}
	public byte[] getClaPic() {
		return claPic;
	}
	public void setClaPic(byte[] claPic) {
		this.claPic = claPic;
	}
}
