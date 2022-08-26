package com.emp.model;

import java.util.List;

public interface EmpDaoImpl {
	public void insert(EmpVO empVO);

	public void update(EmpVO empVO);

	public EmpVO findByPrimaryKey(Integer empid);

	public List<EmpVO> getAll();

	public EmpVO findLatestId();

}
