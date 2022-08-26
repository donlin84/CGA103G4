package com.frreport.model;

public interface ArticleReportDaoImpl {
	public void insert(ArticleReportVO articleReportVO);

	public void update(ArticleReportVO articleReportVO);

	public ArticleReportVO findByPrimaryKey(Integer atcReportid);

}
