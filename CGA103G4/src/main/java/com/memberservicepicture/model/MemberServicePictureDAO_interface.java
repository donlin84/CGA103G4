package com.memberservicepicture.model;

import java.util.List;


public interface MemberServicePictureDAO_interface {
    public void insert(MemberServicePictureVO memberServicePictureVO);
    public void update(MemberServicePictureVO memberServicePictureVO);
    public void delete(Integer mspPicid);
    public MemberServicePictureVO findByPrimaryKey(Integer mspPicid);
    public List<MemberServicePictureVO> getAll();
//    public List<TeacherVO> getAll(Map<String, String[]> map);
}
