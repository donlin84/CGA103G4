package com.keyword.model;

import java.util.List;


public interface KeyWordDAO_interface {
    public void insert(KeyWordVO keyWordVO);
    public void update(KeyWordVO keyWordVO);
    public void delete(Integer kwid);
    public KeyWordVO findByPrimaryKey(Integer kwid);
    public List<KeyWordVO> getAll();
//    public List<TeacherVO> getAll(Map<String, String[]> map);
}
