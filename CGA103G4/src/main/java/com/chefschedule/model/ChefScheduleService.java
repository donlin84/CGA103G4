package com.chefschedule.model;

import java.sql.Date;
import java.util.List;

public class ChefScheduleService {
	private ChefScheduleDAO_interface dao;

	public ChefScheduleService() {
		dao = new ChefScheduleJDBCDAO();
	}

	public ChefScheduleVO addchefChefSchedule(Integer chefid, Date schDate, Integer schTime) {
		ChefScheduleVO chefScheduleVO = new ChefScheduleVO();
		chefScheduleVO.setChefid(chefid);
		chefScheduleVO.setSchDate(schDate);
		chefScheduleVO.setSchTime(schTime);
		dao.insert(chefScheduleVO);
		return chefScheduleVO;
	}

	public ChefScheduleVO updateChefSchedule(Integer schTime,Integer chefid, Date schDate) {
		ChefScheduleVO chefScheduleVO = new ChefScheduleVO();
		chefScheduleVO.setChefid(chefid);
		chefScheduleVO.setSchDate(schDate);
		chefScheduleVO.setSchTime(schTime);
		dao.update(chefScheduleVO);

		return chefScheduleVO;
	}

	public void deleteAnnouncement(Integer chefid, Date schDate) {
		dao.delete(chefid, schDate);
	}

	public List<ChefScheduleVO> getAllById(Integer chefid){
		return dao.getAllById(chefid);
	}
}
