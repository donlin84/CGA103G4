package com.promotions.model;

import java.util.List;
import java.util.Map;

public interface PromotionsDAO_interface {
	public void insert(PromotionsVO promotionsVO);
	public void update(PromotionsVO promotionsVO);
	public void delete(Integer pmid);
	public PromotionsVO findByPrimaryKey(Integer pmid);
	public List<PromotionsVO> getAll();
	public List<PromotionsVO> getAll(Map<String, String[]> map);
}
