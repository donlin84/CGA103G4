package com.frarticlesave.model;

import com.fraritclegood.model.ArticleGoodVO;

public interface ArticleSaveDaoImpl {
	public void insert(ArticleSaveVO articleSaveVO);

	public void delete(Integer atcid);

	public ArticleSaveVO findByPrimaryKey(Integer atcid);

}
