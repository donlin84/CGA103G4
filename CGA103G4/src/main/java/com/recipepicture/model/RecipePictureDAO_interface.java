package com.recipepicture.model;

import java.util.List;

public interface RecipePictureDAO_interface {
	public void insert(RecipePictureVO recipePictureVO);
	public void update(RecipePictureVO recipePictureVO);
	public void delete(Integer rePicid);
	public List<RecipePictureVO> selectALL();
	public RecipePictureVO findByPrimaryKey(Integer rePicid);
}
