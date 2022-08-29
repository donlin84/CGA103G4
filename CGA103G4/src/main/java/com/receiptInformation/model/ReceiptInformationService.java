package com.receiptInformation.model;
import java.util.List;

import com.product.model.ProductVO;

public class ReceiptInformationService {
	private ReceiptInformationDAO_interface dao;
	
	public ReceiptInformationService() {
		dao = new ReceiptInformationJDBCDAO();
	}

	public ReceiptInformationVO addReceiptinformation
	(Integer rcpid, Integer memid, String rcpName, String rcpCvs, String rcpAddress, String rcpPhone)
	{
		
	ReceiptInformationVO receiptInformationVO = new ReceiptInformationVO();
	receiptInformationVO.setRcpid(rcpid);
	receiptInformationVO.setMemid(memid);
	receiptInformationVO.setRcpName(rcpName);
	receiptInformationVO.setRcpCvs(rcpCvs);
	receiptInformationVO.setRcpAddress(rcpAddress);
	receiptInformationVO.setRcpPhone(rcpPhone);
	dao.insert(receiptInformationVO);
	return receiptInformationVO;
	
	}
	
	public ReceiptInformationVO updateReceiptinformation
	(Integer rcpid, Integer memid, String rcpName, String rcpCvs, String rcpAddress, String rcpPhone)
	{
	ReceiptInformationVO receiptInformationVO = new ReceiptInformationVO();
	receiptInformationVO.setRcpid(rcpid);
	receiptInformationVO.setMemid(memid);
	receiptInformationVO.setRcpName(rcpName);
	receiptInformationVO.setRcpCvs(rcpCvs);
	receiptInformationVO.setRcpAddress(rcpAddress);
	receiptInformationVO.setRcpPhone(rcpPhone);
	dao.update(receiptInformationVO);
	return receiptInformationVO;
	}
	public ReceiptInformationVO getOneReceiptinformation(Integer rcpid) {
		return dao.findByPrimaryKey(rcpid);
	}

	public List<ReceiptInformationVO> getAll() {
		return dao.getAll();
	}
	
	
	
	
}
