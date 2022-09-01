package com.promotions.model;

import java.sql.Date;
import java.util.List;

public class PromotionsService {
	
	private PromotionsDAO_interface dao;
	
	public PromotionsService() {
		dao = new PromotionsDAO();
	}
	
	public PromotionsVO addPromotions(String pmName, String pmDescription, Double pmDiscount,
			Date pmStart, Date pmEnd, Integer pmStatus) {
		
		PromotionsVO promotionsVO = new PromotionsVO();
		
		promotionsVO.setPmName(pmName);
		promotionsVO.setPmDescription(pmDescription);
		promotionsVO.setPmDiscount(pmDiscount);
		promotionsVO.setPmStart(pmStart);
		promotionsVO.setPmEnd(pmEnd);
		promotionsVO.setPmStatus(pmStatus);
		dao.insert(promotionsVO);
		
		return promotionsVO;
	}
	public PromotionsVO updatePromotions(Integer pmid, String pmName, String pmDescription, Double pmDiscount,
			Date pmStart, Date pmEnd, Integer pmStatus) {
		
		PromotionsVO promotionsVO = new PromotionsVO();
		
		promotionsVO.setPmid(pmid);
		promotionsVO.setPmName(pmName);
		promotionsVO.setPmDescription(pmDescription);
		promotionsVO.setPmDiscount(pmDiscount);
		promotionsVO.setPmStart(pmStart);
		promotionsVO.setPmEnd(pmEnd);
		promotionsVO.setPmStatus(pmStatus);
		dao.update(promotionsVO);
		
		return promotionsVO;
	}
	
	public void deletePromotions(Integer pmid) {
		dao.delete(pmid);
	}

	public PromotionsVO getOnePromotion(Integer pmid) {
		return dao.findByPrimaryKey(pmid);
	}

	public List<PromotionsVO> getAll() {
		return dao.getAll();
	}
	
}
