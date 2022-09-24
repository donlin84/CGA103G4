package com.member.model;

import java.util.*;


public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);
    public void update(MemberVO memberVO);
    public MemberVO findByPrimaryKey(Integer memid);
    public MemberVO findByAccount(String memAccount);
    public List<MemberVO> getAll();
    public List<MemberVO> getAll(Map<String, String[]> map); 
    public List<Integer> getAllMemid();

}
