package com.product.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.promotions.model.PromotionsVO;
import com.promotionsdetail.model.PromotionsDetailVO;

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
		
		public List<ProductVO> getAllOrderByPdidAsc() {
			return dao.listAllOderByPdid();
		}

		public List<ProductVO> getAllProductName() {
			return dao.getAllPdName();
		}
			
		
		public List<ProductVO> listByPdPriceAsc(){
			return dao.listAllOdByPdPriceAsc();
		}
		public List<ProductVO> listByPdPriceDesc(){
			return dao.listAllOdByPdPriceDesc();
		}
		
		public List<ProductVO> listByPdSort(Integer pdsid) {
			return dao.listByPdSort(pdsid);
		}
		
		public List<ProductVO> listByPdStatus(Integer pdStatus){
			return dao.listByPdStatus(pdStatus);
		}
		public List<ProductVO> getAll(Map<String, String[]> map) {
			return dao.getAll(map);
		}
		public List<Object> GetNewestOne() {
			return dao.getTop3pd();
		}
		public ProductVO getNewestPdid() {
			return dao.getNewestPdid();
		}
		public PromotionsVO getPmDiscount(Integer pmid) {
			return dao.getPromoDiscount(pmid);
		}
		public List<PromotionsDetailVO> getAllandPmid(){
			return dao.getAllJoinPmid();
		}
		public ProductVO updateProduct(Integer pdStatus, Integer pdid) {

			ProductVO productVO = new ProductVO();
			
			productVO.setPdStatus(pdStatus);
			productVO.setPdid(pdid);
			dao.updatePdStatus(productVO);

			return productVO;
		
		}
		public List<ProductVO> PdOnShelfArrangeByPrice() {
			return dao.getPdOnShelfOrderByPrice();
		}
		public List<ProductVO> PdOnShelfArrangeByPriceDesc() {
			return dao.getPdOnShelfOrderByPriceDesc();
		}
		public List<ProductVO> PdOnShelfArrangeByPdUpdate() {
			return dao.getPdOnShelfOrderByPriceDesc();
		}
		public List<ProductVO> ListAllPdByName(String pdName){
			return dao.NameSearchGetAll(pdName);
		}
}		