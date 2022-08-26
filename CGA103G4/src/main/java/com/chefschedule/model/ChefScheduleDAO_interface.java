package com.chefschedule.model;

import java.util.*;

public interface ChefScheduleDAO_interface {
	public void insert(ChefScheduleVO chefScheduleVO);
    public void update(ChefScheduleVO chefScheduleVO);
    public ChefScheduleVO findByPrimaryKey(Integer chefSchid);
    public List<ChefScheduleVO> getAll();
}
