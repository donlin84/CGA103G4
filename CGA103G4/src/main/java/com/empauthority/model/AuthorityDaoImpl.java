package com.empauthority.model;

import java.util.List;

public interface AuthorityDaoImpl {
	public void insert(AuthorityVO authorityVO);

	public void delete(Integer empid, Integer funcid);

	public AuthorityVO findByPrimaryKey(Integer empid);

	public List<AuthorityVO> getAll();
}
