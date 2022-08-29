package com.promotions.model;

import java.util.List;

public interface PromotionsDAO_interface {
	public void insert(PromotionsVO promotionsVO);
	public void update(PromotionsVO promotionsVO);
	public void delete(Integer pmid);
	public PromotionsVO findByPrimaryKey(Integer pmid);
	public List<PromotionsVO> getAll();
}
