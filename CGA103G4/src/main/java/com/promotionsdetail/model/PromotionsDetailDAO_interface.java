package com.promotionsdetail.model;

import java.util.List;

import com.promotions.model.PromotionsVO;

public interface PromotionsDetailDAO_interface {
	public void insert(PromotionsDetailVO promotionsDetailVO);
	public void update(PromotionsDetailVO promotionsDetailVO);
	public void delete(Integer pmid,Integer pdid);
	public List<PromotionsDetailVO> findByPmid(Integer pmid);
	public List<PromotionsDetailVO> findByPdid(Integer pdid);
	public List<PromotionsDetailVO> getAll();
}
