package com.creditcardinformation.model;

import java.util.*;

public interface CreditCardInformationDAO_interface {
	public void insert(CreditCardInformationVO creditCardInformationVO);
	public void delete(Integer creditCardid);
    public CreditCardInformationVO findByPrimaryKey(Integer creditCardid);
    public List<CreditCardInformationVO> getAll();
}
