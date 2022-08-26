package com.frarticle.model;

import java.util.List;

public interface ArticleDaoImpl {
	public void insert(ArticleVO articleVO);

	public void update(ArticleVO articleVO);

	public ArticleVO findByPrimaryKey(Integer atcid);

	public List<ArticleVO> getAll();
}
