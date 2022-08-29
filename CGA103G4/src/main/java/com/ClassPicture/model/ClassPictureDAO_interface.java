package com.ClassPicture.model;

import java.util.List;


public interface ClassPictureDAO_interface {
	public void insert(ClassPictureVO classPictureVO);
	public void update(ClassPictureVO classPictureVO);
	public void delete(Integer claPicid);
	public List<ClassPictureVO> findByPrimaryKey(Integer claPicid);
	public List<ClassPictureVO> getAll();
	
	//同時新增課程資訊 和 課程圖片 1對多
	public void insert2 (ClassPictureVO classPictureVO , java.sql.Connection con);
}
