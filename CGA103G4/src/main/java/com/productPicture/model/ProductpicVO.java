package com.productPicture.model;

public class ProductpicVO implements java.io.Serializable {

	private static final long serialVersionUID = 5066126491333224384L;
	private Integer pdPicid;
	private Integer pdid;
	private byte[] pdPic;
	
	public Integer getPdPicid() {
		return pdPicid;
	}
	public void setPdPicid(Integer pdPicid) {
		this.pdPicid = pdPicid;
	}
	public Integer getPdid() {
		return pdid;
	}
	public void setPdid(Integer pdid) {
		this.pdid = pdid;
	}
	public byte[] getPdPic() {
		return pdPic;
	}
	public void setPdPic(byte[] pdPic) {
		this.pdPic = pdPic;
	}

}
