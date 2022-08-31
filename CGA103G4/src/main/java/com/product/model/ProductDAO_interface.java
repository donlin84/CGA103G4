package com.product.model;

import java.util.*;

public interface ProductDAO_interface {
	public void insert(ProductVO productVO);
	public void update(ProductVO productVO);
	public ProductVO findByPrimaryKey(Integer pdid);
	public List<ProductVO> getAll();
	public List<ProductVO> getAllPdName();
	public List<ProductVO> listByPdSort(Integer pdsid);
	public List<ProductVO> listByPdStatus(Integer pdStatus);
}
