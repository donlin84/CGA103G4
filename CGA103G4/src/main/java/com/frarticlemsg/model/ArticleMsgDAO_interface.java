package com.frarticlemsg.model;

import java.util.List;

public interface ArticleMsgDAO_interface {
	public void insert(ArticleMsgVO articleMsg);

	public void update(ArticleMsgVO articleMsg);

	public ArticleMsgVO findByPrimaryKey(Integer atcMsgid);

	public List<ArticleMsgVO> getAll();
}
