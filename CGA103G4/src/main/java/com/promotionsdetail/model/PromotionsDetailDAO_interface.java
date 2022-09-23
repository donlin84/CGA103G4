package com.promotionsdetail.model;

import java.util.List;
import java.util.Map;

public interface PromotionsDetailDAO_interface {
	public void insert(PromotionsDetailVO promotionsDetailVO);
	public void update(PromotionsDetailVO promotionsDetailVO);
	public PromotionsDetailVO findByPrimaryKey(Integer pmid,Integer pdid);
	public List<PromotionsDetailVO> findByPmid(Integer pmid);
	public List<PromotionsDetailVO> findByPdid(Integer pdid);
	public List<PromotionsDetailVO> getAll();
	public List<JoinAllVO> getAllGetNotPromationsProducts();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<PromotionsDetailVO> getAll(Map<String, String[]> map);
    
//	===================================冠銓新增
	public PromotionsDetailVO getOnePmidByPdid(Integer pdid);
}
