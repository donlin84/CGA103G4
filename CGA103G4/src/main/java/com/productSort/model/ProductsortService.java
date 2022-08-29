package com.productSort.model;

import java.util.List;

public class ProductsortService {
	
	private ProductsortDAO_interface dao;
	
	public ProductsortService() {
		dao = new ProductsortJDBCDAO();
	}
	public ProductsortVO addProductsort(Integer pdsid, String pdsName) 
	{
		
		ProductsortVO productsortVO = new ProductsortVO();
		productsortVO.setPdsid(pdsid);
		productsortVO.setPdsName(pdsName);
		dao.insert(productsortVO);
		return productsortVO;
	
	}
	public ProductsortVO updateProductsort(Integer pdsid, String pdsName) 
	{
	
		ProductsortVO productsortVO = new ProductsortVO();
		productsortVO.setPdsid(pdsid);
		productsortVO.setPdsName(pdsName);
		
		dao.update(productsortVO);
		return productsortVO;
	}
	public ProductsortVO getOneproductsort(Integer pdsid) {
		return dao.findByPrimaryKey(pdsid);
	}

	public List<ProductsortVO> getAll() {
		return dao.getAll();
	}
}