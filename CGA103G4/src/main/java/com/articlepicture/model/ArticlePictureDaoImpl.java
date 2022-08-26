package com.articlepicture.model;

public interface ArticlePictureDaoImpl {
	public void insert(ArticlePictureVO articlePictureVO);

	public void update(ArticlePictureVO articlePictureVO);

	public void delete(Integer atcPicid);

	public ArticlePictureVO findByPrimaryKey(Integer atcPicid);

}
