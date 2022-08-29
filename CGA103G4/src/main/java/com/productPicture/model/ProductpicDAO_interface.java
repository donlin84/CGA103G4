package com.productPicture.model;

import java.util.*;

public interface ProductpicDAO_interface {

	public void insert(ProductpicVO productpicVO);
	public void existedInsert(ProductpicVO productpicVO);
	public void update(ProductpicVO productppicVO);
	public void delete(Integer pdPicid);
	public ProductpicVO findByPrimaryKey(Integer pdPicid);
	public List <ProductpicVO> getAll();

	
}
