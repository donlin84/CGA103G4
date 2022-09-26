package com.product.model;

import java.util.*;

import com.promotions.model.PromotionsVO;
import com.promotionsdetail.model.PromotionsDetailVO;

public interface ProductDAO_interface {
	public void insert(ProductVO productVO);
	public void update(ProductVO productVO);
	public void updatePdStatus(ProductVO productVO);
//	==========================================================================
	public ProductVO findByPrimaryKey(Integer pdid);
	public List<ProductVO> getAll();  	   
	public List<ProductVO> getAllPdName(); //檢查商品名重複用
	public List<ProductVO> listByPdSort(Integer pdsid);
	public List<ProductVO> listByPdStatus(Integer pdStatus);

	public List<ProductVO> listAllOderByPdid();

	public List<ProductVO> listAllOdByPdPriceDesc();
	public List<ProductVO> listAllOdByPdPriceAsc();
	public ProductVO getNewestPdid();//查id用
	
	public List<ProductVO> getAll(Map<String, String[]> map); // 複合查詢
	
	public List<ProductVO> NameSearchGetAll(String pdName);  
//	==========================================================================
	public List<PromotionsDetailVO>getAllJoinPmid();
	public List<Object> getTop3pd();
	public PromotionsVO getPromoDiscount(Integer pmid);
	
	public List<ProductVO> getPdOnShelfOrderByPrice();
	public List<ProductVO> getPdOnShelfOrderByPriceDesc();
	
	public List<ProductVO> getPdOnShelfOrderPdUpdate();
	
	
}
