package com.chefappointmentform.model;

import java.sql.Date;
import java.util.List;

import com.chefschedule.model.ChefScheduleDAO_interface;
import com.chefschedule.model.ChefScheduleJDBCDAO;
import com.chefschedule.model.ChefScheduleVO;
import com.emp.model.EmpVO;

public class ChefAppointmentFormService {
	private ChefAppointmentFormDAO_interface dao;

	public ChefAppointmentFormService() {
		dao = new ChefAppointmentFormJDBCDAO();
	}

	public ChefAppointmentFormVO addAppointment(Integer memid , Integer chefid, Date apmDate, Integer apmTime, Integer apmPrice) {
		ChefAppointmentFormVO chefAppointmentFormVO = new ChefAppointmentFormVO();
		chefAppointmentFormVO.setMemid(memid);
		chefAppointmentFormVO.setChefid(chefid);
		chefAppointmentFormVO.setApmDate(apmDate);
		chefAppointmentFormVO.setApmTime(apmTime);
		chefAppointmentFormVO.setApmPrice(apmPrice);

		dao.insert(chefAppointmentFormVO);
		return chefAppointmentFormVO;
	}

	public ChefAppointmentFormVO updteAppointmentByChef(Integer apmid,Date apmDate, Integer apmTime, Integer apmPrice, Integer apmStatus) {
		ChefAppointmentFormVO chefAppointmentFormVO = new ChefAppointmentFormVO();
		chefAppointmentFormVO.setApmid(apmid);
		chefAppointmentFormVO.setApmDate(apmDate);
		chefAppointmentFormVO.setApmTime(apmTime);
		chefAppointmentFormVO.setApmPrice(apmPrice);
		chefAppointmentFormVO.setApmStatus(apmStatus);
		dao.updateByChef(chefAppointmentFormVO);

		return chefAppointmentFormVO;
	}
	public ChefAppointmentFormVO updteAppointmentByMem(Integer apmid,Integer star, String comments) {
		ChefAppointmentFormVO chefAppointmentFormVO = new ChefAppointmentFormVO();
		chefAppointmentFormVO.setApmid(apmid);
		chefAppointmentFormVO.setStar(star);
		chefAppointmentFormVO.setComments(comments);
		dao.updateByMem(chefAppointmentFormVO);
		
		return chefAppointmentFormVO;
	}

	public ChefAppointmentFormVO getOneChefApp(Integer ampid) {
		return dao.findByapmid(ampid);
	}

	public List<ChefAppointmentFormVO> getAllByChefId(Integer chefid){
		return dao.getAllByChef(chefid);
	}
	public List<ChefAppointmentFormVO> getAllByMemId(Integer memid){
		return dao.getAllByMem(memid);
	}
}
