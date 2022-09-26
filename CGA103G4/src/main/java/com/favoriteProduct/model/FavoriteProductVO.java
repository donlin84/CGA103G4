package com.favoriteProduct.model;

import com.product.model.ProductDAO;
import com.product.model.ProductVO;
import com.promotions.model.PromotionsDAO;
import com.promotions.model.PromotionsVO;

public class FavoriteProductVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer pdid;
	private Integer memid;
	
	public Integer getPdid() {
		return pdid;
	}
	public void setPdid(Integer pdid) {
		this.pdid = pdid;
	}
	public Integer getMemid() {
		return memid;
	}
	public void setMemid(Integer memid) {
		this.memid = memid;
	}
	


	public com.product.model.ProductVO getProductVO(){
		
		com.product.model.ProductService pdSvc = new com.product.model.ProductService();
		com.product.model.ProductVO productVO = pdSvc.getOneproduct(pdid);
		return productVO;
	}


}
