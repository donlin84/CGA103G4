package com.favoriteProduct.model;

import java.util.List;

public class FavoriteProductService {

	private FavoriteProductDAO_interface dao;
	
	public FavoriteProductService() {
		dao = new FavoriteProductDAO();
	}
	
	public FavoriteProductVO addFavoriteProduct(Integer pdid, Integer memid) {
		
		FavoriteProductVO favoriteProductVO = new FavoriteProductVO();
		favoriteProductVO.setPdid(pdid);
		favoriteProductVO.setMemid(memid);
		dao.insert(favoriteProductVO);
		
		return favoriteProductVO;
	}
	public FavoriteProductVO updateFavoriteProduct(Integer pdid, Integer memid) {
		
		FavoriteProductVO favoriteProductVO = new FavoriteProductVO();
		
		favoriteProductVO.setPdid(pdid);
		favoriteProductVO.setMemid(memid);
		dao.update(favoriteProductVO);
		
		return favoriteProductVO;
	}
	public void deleteFavoriteProduct(Integer memid, Integer pdid) {
		dao.delete(memid,pdid);
	}
	public List<FavoriteProductVO> getAllFavoriteOneUser(Integer memid){
		
		return dao.getAllFavoriteOneUser(memid);	
	}
}
	


	
		
	
	
	

