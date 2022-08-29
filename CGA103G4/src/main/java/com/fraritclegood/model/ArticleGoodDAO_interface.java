package com.fraritclegood.model;

import java.util.List;

import com.frarticle.model.ArticleVO;

public interface ArticleGoodDAO_interface {
	public void insert(ArticleGoodVO articleGoodVO);

	public void delete(Integer memid);

	public ArticleGoodVO findByPrimaryKey(Integer atcid);

}
