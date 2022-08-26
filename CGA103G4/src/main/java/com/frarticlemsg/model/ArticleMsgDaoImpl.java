package com.frarticlemsg.model;

import java.util.List;

public interface ArticleMsgDaoImpl {
	public void insert(ArticleMsgVO articleMsg);

	public void update(ArticleMsgVO articleMsg);

	public ArticleMsgVO findByPrimaryKey(Integer atcMsgid);

	public List<ArticleMsgVO> getAll();
}
