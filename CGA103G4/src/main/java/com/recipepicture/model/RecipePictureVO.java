package com.recipepicture.model;

import java.io.Serializable;

public class RecipePictureVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer rePicid;
	private Integer reid;
	private byte[] rePic;
	
	public RecipePictureVO() {
		
	}
	
	public Integer getRePicid() {
		return rePicid;
	}
	public void setRePicid(Integer pePicid) {
		this.rePicid = pePicid;
	}
	public Integer getReid() {
		return reid;
	}
	public void setReid(Integer reid) {
		this.reid = reid;
	}
	public byte[] getRePic() {
		return rePic;
	}
	public void setRePic(byte[] rePic) {
		this.rePic = rePic;
	}
	
}
