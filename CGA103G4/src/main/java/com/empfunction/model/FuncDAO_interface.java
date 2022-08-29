package com.empfunction.model;

import java.util.List;

public interface FuncDAO_interface {
	public void insert(FuncVO FuncVO);

	public void update(FuncVO FuncVO);

	public FuncVO findByPrimaryKey(Integer funcid);

	public List<FuncVO> getAll();
}
