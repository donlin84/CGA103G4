package com.emp.model;

import java.sql.Date;
import java.util.List;

public class EmpService {

	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpDAO();
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

	public EmpVO getOneEmp(Integer empid) {
		return dao.findByPrimaryKey(empid);
	}

	public List<EmpVO> getAll() {
		return dao.getAll();
	}

	public EmpVO findLatestId() {
		return dao.findLatestId();
	}
}
