package com.chefskillstype.model;

import java.util.*;

public interface ChefSkillsTypeDAO_interface {
	public void insert(ChefSkillsTypeVO chefskillstypeVO);
    public void update(ChefSkillsTypeVO chefskillstypeVO);
    public ChefSkillsTypeVO findByPrimaryKey(Integer skillid);
    public List<ChefSkillsTypeVO> getAll();
}
