package com.creditcardinformation.model;

import java.util.List;


public class CreditCardInformationService {
	private CreditCardInformationDAO_interface dao;

	public CreditCardInformationService() {
		dao = new CreditCardInformationJDBCDAO();
	}

	public CreditCardInformationVO addCreditCardInformation(Integer memid, String creditCardNumber, String creditCardName,
			String creditCardTime, String cvcCode) {

		CreditCardInformationVO creditCardInformationVO = new CreditCardInformationVO();

		creditCardInformationVO.setMemid(memid);
		creditCardInformationVO.setCreditCardNumber(creditCardNumber);
		creditCardInformationVO.setCreditCardName(creditCardName);
		creditCardInformationVO.setCreditCardTime(creditCardTime);
		creditCardInformationVO.setCvcCode(cvcCode);
		dao.insert(creditCardInformationVO);

		return creditCardInformationVO;
	}

	public void deleteCreditCardInformation(Integer creditCardid) {
		dao.delete(creditCardid);
	}

	public CreditCardInformationVO getOneCreditCardInformation(Integer creditCardid) {
	return dao.findByPrimaryKey(creditCardid);
	}

	public List<CreditCardInformationVO> getAll() {
		return dao.getAll();
	}
}
