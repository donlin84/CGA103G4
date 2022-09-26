package com.product.model;

import java.time.LocalDateTime;

import java.time.temporal.ChronoUnit;
import java.util.List;

import com.productPicture.model.ProductpicDAO;
import com.productPicture.model.ProductpicDAO_interface;
import com.productPicture.model.ProductpicVO;
import com.promotions.model.PromotionsDAO;
import com.promotions.model.PromotionsVO;



public class ProductVO implements java.io.Serializable {
	
	private static final long serialVersionUID = -7792255716423459227L;
	private Integer pdid;
	private	Integer pdsid;
	private	String pdName;
	private	Integer pdPrice;
	private	Integer	pdDiscountPrice;
	private String pdDescription;
	private Integer pdStatus;
	private LocalDateTime pdUpdate;
	
	
	
	public Integer getPdid() {
		return pdid;
	}
	public void setPdid(Integer pdid) {
		this.pdid = pdid;
	}
	public Integer getPdsid() {
		return pdsid;
	}
	public void setPdsid(Integer pdsid) {
		this.pdsid = pdsid;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public Integer getPdPrice() {
		return pdPrice;
	}
	public void setPdPrice(Integer pdPrice) {
		this.pdPrice = pdPrice;
	}
	public Integer getPdDiscountPrice() {
		return pdDiscountPrice;
	}
	public void setPdDiscountPrice(Integer pdDiscountPrice) {
		this.pdDiscountPrice = pdDiscountPrice;
	}
	public String getPdDescription() {
		return pdDescription;
	}
	public void setPdDescription(String pdDescription) {
		this.pdDescription = pdDescription;
	}
	public Integer getPdStatus() {
		return pdStatus;
	}
	public void setPdStatus(Integer pdStatus) {
		this.pdStatus = pdStatus;
	}
	public LocalDateTime getPdUpdate() {
		
		return pdUpdate.truncatedTo(ChronoUnit.SECONDS);
	}
	public void setPdUpdate(LocalDateTime pdUpdate) {
		this.pdUpdate = pdUpdate;
	}
	//join pd分類名稱到商品
	public com.productSort.model.ProductsortVO getProductSortVO(){
		com.productSort.model.ProductsortService pdsSvc = new com.productSort.model.ProductsortService();
		com.productSort.model.ProductsortVO productsortVO = pdsSvc.getOneproductsort(pdsid);
	return productsortVO;
	}
	
	//join優惠方案名稱到商品
	public com.promotionsdetail.model.PromotionsDetailVO getPromotionsDetailVO(){
		com.promotionsdetail.model.PromotionsDetailService promoSvc = 
		new com.promotionsdetail.model.PromotionsDetailService();
		
		com.promotionsdetail.model.PromotionsDetailVO promotionsDetailVO = promoSvc.getOnePmByPdid(pdid);
		return promotionsDetailVO;
	}
	
	public PromotionsVO getPromotionsVOByPmid(Integer pmid){
		PromotionsDAO dao = new PromotionsDAO();
		PromotionsVO promotionsVO = dao.findByPrimaryKey(pmid);
		return promotionsVO;
	}
//	public MemVO getMemVObyMemNo(Integer memNo) {
//		MemJDBCDAO dao = new MemJDBCDAO();
//		MemVO memVO=dao.getOneByMemNo(memNo);
//		return memVO;
//	}
	
	public List<ProductpicVO> getPicsNo() {
		ProductpicDAO_interface dao = new ProductpicDAO();
		return dao.getOneProductPics(pdid);

	}
	
}
