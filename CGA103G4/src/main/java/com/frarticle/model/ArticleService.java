package com.frarticle.model;

import java.util.List;


public class ArticleService {
	private ArticleDAO_interface dao;

	public ArticleService() {
		dao = new ArticleJDBCDAO();
	}

	public ArticleVO addatc(Integer frid,Integer memid, String atcTitle,String atcContent) {
		ArticleVO articleVO=new ArticleVO();
		articleVO.setFrid(frid);
		articleVO.setMemid(memid);
		articleVO.setAtcTitle(atcTitle);
		articleVO.setAtcContent(atcContent);
		dao.insert(articleVO);
		return articleVO;
	}

	public ArticleVO updateatc(Integer atcid,String atcContent) {
		ArticleVO articleVO=new ArticleVO();
		articleVO.setAtcid(atcid);
		articleVO.setAtcContent(atcContent);
		dao.update(articleVO);
		return articleVO;
	}

	public ArticleVO getOneatc(Integer atcid) {
		return dao.findByPrimaryKey(atcid);
	}

	public ArticleVO updateStatus(Integer atcid,Integer atcStatus) {
		ArticleVO articleVO=new ArticleVO();
		articleVO.setAtcid(atcid);
		articleVO.setAtcStatus(atcStatus);
		dao.updateStatus(articleVO);
		return articleVO;
	}
	
	public List<ArticleVO> getAll() {
		return dao.getAll();
	}

}
