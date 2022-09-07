package com.chef.model;

import java.util.*;

public interface ChefDAO_interface {
	 public void insert(ChefVO chefVO);
     public void update(ChefVO chefVO);
     public ChefVO findByPrimaryKey(Integer chefid);
     public List<ChefVO> getAll();
     public List<ChefVO> getAll(Map<String, String[]> map); 
}
