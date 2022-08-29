package com.frmsgreport.model;

import com.frreport.model.ArticleReportVO;

public interface MsgReportDAO_interface {
	public void insert(MsgReportVO msgReportVO);

	public void update(MsgReportVO msgReportVO);

	public MsgReportVO findByPrimaryKey(Integer msgReportid);

}
