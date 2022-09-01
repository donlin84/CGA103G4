package com.emp.model;

import java.util.List;
import java.util.Set;

import com.announcement.model.AnnouncementVO;

public interface EmpDAO_interface {
	public void insert(EmpVO empVO);

	public void update(EmpVO empVO);

	public EmpVO findByPrimaryKey(Integer empid);

	public List<EmpVO> getAll();

	public EmpVO findLatestId();

	public Set<AnnouncementVO> getAnnouncementByEmpid(Integer annid);
	
	public EmpVO get_one_account(String empAccount);

}
