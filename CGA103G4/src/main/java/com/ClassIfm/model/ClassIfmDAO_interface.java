package com.ClassIfm.model;

import java.util.*;

import com.ClassPicture.model.ClassPictureVO;


public interface ClassIfmDAO_interface {
    public void insert(ClassIfmVO classIfmVO);
    public void update(ClassIfmVO classIfmVO);
//  public void delete(Integer thrid);
    public ClassIfmVO findByPrimaryKey(Integer claid);
    public List<ClassIfmVO> getAll();
    
    //同時新增課程資訊 和 課程圖片 1對多
    public void insertwithclapic(ClassIfmVO classIfmVO,List<ClassPictureVO> list);
}
