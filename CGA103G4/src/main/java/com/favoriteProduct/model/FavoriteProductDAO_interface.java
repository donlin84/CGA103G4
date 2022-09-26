package com.favoriteProduct.model;

import java.util.List;

public interface FavoriteProductDAO_interface {
	public void insert(FavoriteProductVO favoriteProductVO);
	public void update(FavoriteProductVO favoriteProductVO);
	public FavoriteProductVO findbyPrimaryKey(Integer pdid);
	public List<FavoriteProductVO> getAll();
	public void delete(Integer memid, Integer pdid);
	public List<FavoriteProductVO> getAllFavoriteOneUser(Integer memid);
	
	
	

}
