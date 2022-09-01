package com.chef.model;

import java.util.*;

public interface ChefDAO_interface {
	 public void insert(ChefVO chefVO);
     public void update(ChefVO chefVO);
     public ChefVO findByPrimaryKey(Integer chefid);
     public List<ChefVO> getAll();
     public ChefVO get_one_account(String chefAccount);

}
