package com.chefskills.model;

import java.util.*;

public interface ChefSkillsDAO_interface {
	public void insert(ChefSkillsVO chefSkillsVO);
    public void update(ChefSkillsVO chefSkillsVO);
    public void delete(Integer chefid, Integer skillsid);
    public ChefSkillsVO findByPrimaryKey(Integer chefid, Integer skillsid);
    public List<ChefSkillsVO> getAll();
}
