package com.promotionsdetail.model;

import java.util.List;

public class PromotionsDetailService {
	
	private PromotionsDetailDAO_interface dao;
	
	public PromotionsDetailService() {
		dao = new PromotionsDetailDAO();
	}
	
	public PromotionsDetailVO addPromotionsDetail(Integer pmid, Integer pdid, Integer pmPdDiscountPrice) {
		
		PromotionsDetailVO promotionsDetailVO = new PromotionsDetailVO();
		
		promotionsDetailVO.setPdid(pdid);
		promotionsDetailVO.setPmid(pmid);
		promotionsDetailVO.setPmPdDiscountPrice(pmPdDiscountPrice);
		dao.insert(promotionsDetailVO);
		
		return promotionsDetailVO;
	}
	
	public PromotionsDetailVO updatePromotionsDetail(Integer pmid, Integer pdid, Integer pmPdDiscountPrice) {
		
		PromotionsDetailVO promotionsDetailVO = new PromotionsDetailVO();
		
		promotionsDetailVO.setPdid(pdid);
		promotionsDetailVO.setPmid(pmid);
		promotionsDetailVO.setPmPdDiscountPrice(pmPdDiscountPrice);
		dao.update(promotionsDetailVO);
		
		return promotionsDetailVO;
	}
	
	public PromotionsDetailVO getOnePromotionDetail(Integer pmid,Integer pdid) {
		return dao.findByPrimaryKey(pmid,pdid);
	}

	public List<PromotionsDetailVO> getParticipatePromotionsProduct(Integer pmid) {
		return dao.findByPmid(pmid);
	}
	
	public List<PromotionsDetailVO> getProductParticipatePromotions(Integer pdid) {
		return dao.findByPdid(pdid);
	}

	public List<PromotionsDetailVO> getAll() {
		return dao.getAll();
	}
	
}
