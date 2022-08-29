package com.productSort.model;

import java.util.*;

public interface ProductsortDAO_interface {
	public void insert (ProductsortVO productsortVO);
	public void update (ProductsortVO ProductsortVO);
	public ProductsortVO findByPrimaryKey(Integer pdsid);
	public List<ProductsortVO> getAll();

}
