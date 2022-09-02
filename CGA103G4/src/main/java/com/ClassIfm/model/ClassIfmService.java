package com.ClassIfm.model;

import java.time.LocalDateTime;
import java.util.List;

import com.ClassPicture.model.ClassPictureVO;

public class ClassIfmService {

	private ClassIfmDAO_interface dao;
	
	public ClassIfmService() {
		dao = new ClassIfmJDBCDAO();
	}
	
	public ClassIfmVO addClassIfm(Integer thrid,Integer claTagid,String claTitle,String claIntroduction,LocalDateTime claTime,Integer claPrice,Integer claPeopleMax,Integer claPeopleMin,Integer claPeople,Integer claStatus,LocalDateTime claStrTime,LocalDateTime claFinTime) {
		
		ClassIfmVO classIfmVO = new ClassIfmVO();
		
		classIfmVO.setThrid(thrid);
		classIfmVO.setClaTagid(claTagid);
		classIfmVO.setClaTitle(claTitle);
		classIfmVO.setClaIntroduction(claIntroduction);
		classIfmVO.setClaTime(claTime);
		classIfmVO.setClaPrice(claPrice);
		classIfmVO.setClaPeopleMax(claPeopleMax);
		classIfmVO.setClaPeopleMin(claPeopleMin);
		classIfmVO.setClaPeople(claPeople);
		classIfmVO.setClaStatus(claStatus);
		classIfmVO.setClaStrTime(claStrTime);
		classIfmVO.setClaFinTime(claFinTime);
		dao.insert(classIfmVO);

		return classIfmVO;
	}
	
	
public ClassIfmVO updateClassIfm(Integer claid,Integer thrid,Integer claTagid,String claTitle,String claIntroduction,LocalDateTime claTime,Integer claPrice,Integer claPeopleMax,Integer claPeopleMin,Integer claPeople,Integer claStatus,LocalDateTime claStrTime,LocalDateTime claFinTime) {
		
		ClassIfmVO classIfmVO = new ClassIfmVO();
		
		classIfmVO.setClaid(claid);
		classIfmVO.setThrid(thrid);
		classIfmVO.setClaTagid(claTagid);
		classIfmVO.setClaTitle(claTitle);
		classIfmVO.setClaIntroduction(claIntroduction);
		classIfmVO.setClaTime(claTime);
		classIfmVO.setClaPrice(claPrice);
		classIfmVO.setClaPeopleMax(claPeopleMax);
		classIfmVO.setClaPeopleMin(claPeopleMin);
		classIfmVO.setClaPeople(claPeople);
		classIfmVO.setClaStatus(claStatus);
		classIfmVO.setClaStrTime(claStrTime);
		classIfmVO.setClaFinTime(claFinTime);
		dao.update(classIfmVO);

		return classIfmVO;
	}

//	public void deleteClassIfm(Integer claid) {
//		dao.delete(claid);
//	}
	
	public ClassIfmVO getOneClassIfm(Integer claid) {
		return dao.findByPrimaryKey(claid);
	}
	
	public List<ClassIfmVO> getAll(){
		return dao.getAll();
	}
	//單一更新報名人數
	public ClassIfmVO update_clapeople(Integer claPeople,Integer claid) {
		
		ClassIfmVO classIfmVO = new ClassIfmVO();
		classIfmVO.setClaPeople(claPeople);
		classIfmVO.setClaid(claid);
		dao.update_clapeople(classIfmVO);
		return classIfmVO;
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
