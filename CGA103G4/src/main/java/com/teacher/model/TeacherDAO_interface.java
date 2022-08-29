package com.teacher.model;

import java.util.*;

public interface TeacherDAO_interface {
          public void insert(TeacherVO teacherVO);
          public void update(TeacherVO teacherVO);
////          public void delete(Integer thrid);
          public TeacherVO findByPrimaryKey(Integer thrid);
          public List<TeacherVO> getAll();
//          public List<TeacherVO> getAll(Map<String, String[]> map); 
}
