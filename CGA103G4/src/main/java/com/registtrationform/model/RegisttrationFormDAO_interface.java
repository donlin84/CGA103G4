package com.registtrationform.model;

import java.util.List;


public interface RegisttrationFormDAO_interface {
    public void insert(RegisttrationFormVO registtrationFormVO);
    public void update(RegisttrationFormVO registtrationFormVO);
////    public void delete(Integer thrid);
    public RegisttrationFormVO findByPrimaryKey(Integer claid,Integer memid);
    public List<RegisttrationFormVO> getAll();
    public Integer getConutPeopleByClaid(Integer claid);
//    public List<TeacherVO> getAll(Map<String, String[]> map); 
    
    //給timer用的 拿到memid
    public List<RegisttrationFormVO> timer_getmemid(Integer claid);
    //單獨宿改訂單狀態
    public void update_status(Integer claid,Integer memid);
    //用來修改評分及評語
    public void update_review(RegisttrationFormVO registtrationFormVO);
}
