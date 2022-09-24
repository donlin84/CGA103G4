package com.chef.model;

import java.util.*;

public interface ChefDAO_interface {
	 public void insert(ChefVO chefVO);
     public void update(ChefVO chefVO);
     public ChefVO findByPrimaryKey(Integer chefid);
     public List<ChefVO> getAll();
     public List<ChefVO> getAll(Map<String, String[]> map); 
     
     //後台登入
     public ChefVO get_one_account(String chefAccount);
}
