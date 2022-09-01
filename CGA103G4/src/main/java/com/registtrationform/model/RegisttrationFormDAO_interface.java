package com.registtrationform.model;

import java.util.List;


public interface RegisttrationFormDAO_interface {
    public void insert(RegisttrationFormVO registtrationFormVO);
    public void update(RegisttrationFormVO registtrationFormVO);
////    public void delete(Integer thrid);
    public RegisttrationFormVO findByPrimaryKey(Integer claid,Integer memid);
    public List<RegisttrationFormVO> getAll();
//  public List<TeacherVO> getAll(Map<String, String[]> map); 
//拿報名人數
}
