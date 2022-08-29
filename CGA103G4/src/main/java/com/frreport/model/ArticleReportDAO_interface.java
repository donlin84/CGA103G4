package com.frreport.model;

public interface ArticleReportDAO_interface {
	public void insert(ArticleReportVO articleReportVO);

	public void update(ArticleReportVO articleReportVO);

	public ArticleReportVO findByPrimaryKey(Integer atcReportid);

}
