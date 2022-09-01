package com.emp.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.announcement.model.AnnouncementVO;

import groovy.transform.builder.InitializerStrategy.SET;

public class EmpHibernateService {
	
	private static EmpDAO_interface dao;
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config2-JndiObjectFactoryBean.xml");
		dao =(EmpDAO_interface) context.getBean("empHibernateDAO");
	}

	public EmpVO addEmp(String empName, String empPhone, byte[] empPicture, String empAccount, String empPassword,
			Integer empLevel, Date empHiredate) {
		
		EmpVO empVO = new EmpVO();
		
		empVO.setEmpName(empName);
		empVO.setEmpPhone(empPhone);
		empVO.setEmpPicture(empPicture);
		empVO.setEmpAccount(empAccount);
		empVO.setEmpPassword(empPassword);
		empVO.setEmpLevel(empLevel);
		empVO.setEmpHiredate(empHiredate);
		
		dao.insert(empVO);
		
		return empVO;
	}
	
	public void addEmp(EmpVO empVO) {
		dao.insert(empVO);
	}
	
	public EmpVO updateEmp(Integer empid, String empName, String empPhone, byte[] empPicture, String empAccount, String empPassword,
			Integer empLevel,Integer empStatus, Date empHiredate) {
		
		EmpVO empVO = new EmpVO();
		empVO.setEmpid(empid);
		empVO.setEmpName(empName);
		empVO.setEmpPhone(empPhone);
		empVO.setEmpPicture(empPicture);
		empVO.setEmpAccount(empAccount);
		empVO.setEmpPassword(empPassword);
		empVO.setEmpLevel(empLevel);
		empVO.setEmpStatus(empStatus);
		empVO.setEmpHiredate(empHiredate);
		
		dao.update(empVO);
		
		return empVO;
		
	}
	
	public void updateEmp(EmpVO empVO) {
		dao.update(empVO);
	}

	public EmpVO getOneEmp(Integer empid) {
		return dao.findByPrimaryKey(empid);
	}
	
	public Set<AnnouncementVO> getAnnouncementByEmpid(Integer annid) {
		return dao.getAnnouncementByEmpid(annid);
	}
	
	public List<EmpVO> getAll() {
		return dao.getAll();
	}
	
}
