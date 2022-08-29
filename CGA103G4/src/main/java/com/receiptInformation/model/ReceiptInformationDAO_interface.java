package com.receiptInformation.model;

import java.util.*;

public interface ReceiptInformationDAO_interface {
	public void insert(ReceiptInformationVO receiptInformationVO);
    public void update(ReceiptInformationVO receiptInformationVO);
    public void delete(Integer rcpid);
    public ReceiptInformationVO findByPrimaryKey(Integer receiptinformationVO);
    public List<ReceiptInformationVO> getAll();

}
