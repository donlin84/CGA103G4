package com.recipepicture.model;

import java.util.List;

public class RecipePictureService {
	private RecipePictureDAO_interface dao;
	
	public RecipePictureService() {
		dao = new RecipePictureDAO();
	}
	
	public RecipePictureVO addRecipePicture(Integer reid, byte[] rePic) {
		RecipePictureVO recipePictureVO = new RecipePictureVO();
		
		recipePictureVO.setReid(reid);
		recipePictureVO.setRePic(rePic);
		
		dao.insert(recipePictureVO);
		
		return recipePictureVO;
	}
	
	public RecipePictureVO updateRecipePicture (Integer reid, byte[] rePic, Integer rePicid) {
		RecipePictureVO recipePictureVO = new RecipePictureVO();
		
		recipePictureVO.setReid(reid);
		recipePictureVO.setRePic(rePic);
		recipePictureVO.setRePicid(rePicid);
		
		dao.update(recipePictureVO);
		
		return recipePictureVO;
	}
	
	public RecipePictureVO getOne(Integer rePicid) {
		return dao.findByPrimaryKey(rePicid);
	}
	
	public List<RecipePictureVO> getAll() {
		return dao.selectALL();
	}
	
	public void deleteRecipePicture(Integer rePicid) {
		dao.delete(rePicid);
	}
}
