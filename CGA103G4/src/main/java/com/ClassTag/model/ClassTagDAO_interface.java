package com.ClassTag.model;

import java.util.List;

public interface ClassTagDAO_interface {
	public void insert(ClassTagVO classTagVO);
	public void update(ClassTagVO classTagVO);
	public void delete(Integer claTagid);
	public ClassTagVO findByPrimaryKey(Integer claTagid);
	public List<ClassTagVO> getAll();
}
