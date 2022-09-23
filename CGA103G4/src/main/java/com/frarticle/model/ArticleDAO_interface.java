package com.frarticle.model;

import java.util.List;

public interface ArticleDAO_interface {
	public void insert(ArticleVO articleVO);

	public void update(ArticleVO articleVO);

	public ArticleVO findByPrimaryKey(Integer atcid);
	
	public void updateStatus(ArticleVO articleVO);
	
	public List<ArticleVO> getAll();
}
