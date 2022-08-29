package com.favoriteProduct.model;


public class FavoriteProductService {

	private FavoriteProductDAO_interface dao;
	
	public FavoriteProductService() {
		dao = new FavoriteProductJDBCDAO();
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
	public void deleteFavoriteProduct(Integer pdid) {
		dao.delete(pdid);
	}
}
	


	
		
	
	
	

