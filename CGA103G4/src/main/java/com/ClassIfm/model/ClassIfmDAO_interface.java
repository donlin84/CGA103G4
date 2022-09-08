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
    //單一更新報名人數
    public void update_clapeople(ClassIfmVO classIfmVO);
    //前台萬用查詢
    public List<ClassIfmVO> cangetall(String xxx);
    //前台只查詢以上架客程
    public List<ClassIfmVO> front_getall();
    //給email的timer
    public List<ClassIfmVO> timer_getcancel();
    //單一修改課程狀態
    public void update_clastatus(ClassIfmVO classIfmVO);
}
