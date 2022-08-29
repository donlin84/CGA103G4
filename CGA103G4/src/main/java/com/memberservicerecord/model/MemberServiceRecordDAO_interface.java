package com.memberservicerecord.model;

import java.util.List;

public interface MemberServiceRecordDAO_interface {
	
    public void insert(MemberServiceRecordVO memberServiceRecordVO);
    public void update(MemberServiceRecordVO memberServiceRecordVO);
    public void delete(Integer msrid);
    public MemberServiceRecordVO findByPrimaryKey(Integer msrid);
    public List<MemberServiceRecordVO> getAll();
//    public List<TeacherVO> getAll(Map<String, String[]> map);
}
