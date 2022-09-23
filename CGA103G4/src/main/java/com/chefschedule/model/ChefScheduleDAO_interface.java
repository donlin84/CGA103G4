package com.chefschedule.model;

import java.util.*;
import java.sql.Date;
public interface ChefScheduleDAO_interface {
	public void insert(ChefScheduleVO chefScheduleVO);
    public void update(ChefScheduleVO chefScheduleVO);
    public void delete(Integer chefid, Date schDate);
    public List<ChefScheduleVO> getAllById(Integer chefid);
}
