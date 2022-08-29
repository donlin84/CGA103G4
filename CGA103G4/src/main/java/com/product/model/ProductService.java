package com.product.model;

import java.time.LocalDateTime;
import java.util.List;

public class ProductService {
	
	private ProductDAO_interface dao;
	
	public ProductService() {
		dao = new ProductDAO();
	}
		
	public ProductVO addProduct(Integer pdsid, String pdName, Integer pdPrice, Integer pdDiscountPrice, 
			String pdDescription, Integer pdStatus,  LocalDateTime pdUpdate
				) {
			ProductVO productVO = new ProductVO();
			productVO.setPdsid(pdsid);
			productVO.setPdName(pdName);
			productVO.setPdPrice(pdPrice);
			productVO.setPdDiscountPrice(pdDiscountPrice);
			productVO.setPdDescription(pdDescription);
			productVO.setPdStatus(pdStatus);
			productVO.setPdUpdate(pdUpdate);
			dao.insert(productVO);
		
			return productVO;
		}

		public ProductVO updateProduct(Integer pdid, Integer pdsid, String pdName, Integer pdPrice, Integer pdDiscountPrice, 
				String pdDescription, Integer pdStatus,  LocalDateTime pdUpdate
				) {

			ProductVO productVO = new ProductVO();
			productVO.setPdid(pdid);
			productVO.setPdsid(pdsid);
			productVO.setPdName(pdName);
			productVO.setPdPrice(pdPrice);
			productVO.setPdDiscountPrice(pdDiscountPrice);
			productVO.setPdDescription(pdDescription);
			productVO.setPdStatus(pdStatus);
			productVO.setPdUpdate(pdUpdate);
			dao.update(productVO);

			return productVO;
		
		}

		public ProductVO getOneproduct(Integer pdid) {
			return dao.findByPrimaryKey(pdid);
		}

		public List<ProductVO> getAll() {
			return dao.getAll();
		}
	}