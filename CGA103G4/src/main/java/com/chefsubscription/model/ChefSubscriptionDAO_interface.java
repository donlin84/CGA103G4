package com.chefsubscription.model;

import java.util.*;



public interface ChefSubscriptionDAO_interface {
	public void insert(ChefSubscriptionVO chefSubscriptionVO);
	public void delete(Integer chefid, Integer memid);
    public ChefSubscriptionVO findByPrimaryKey(Integer chefid, Integer memid);
    public List<ChefSubscriptionVO> getAll();
    public List<ChefSubscriptionVO> getAll(Map<String, String[]> map); 
}
