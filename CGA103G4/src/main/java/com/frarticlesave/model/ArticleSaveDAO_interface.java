package com.frarticlesave.model;

import java.util.List;

import com.fraritclegood.model.ArticleGoodVO;
import com.frarticle.model.ArticleVO;

public interface ArticleSaveDAO_interface {
	public void insert(ArticleSaveVO articleSaveVO);

	public void delete(Integer atcid);

	public ArticleSaveVO findByPrimaryKey(Integer atcid);
	


}
