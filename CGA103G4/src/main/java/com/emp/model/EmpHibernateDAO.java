package com.emp.model;

import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.announcement.model.AnnouncementVO;

@Transactional(readOnly = true)
public class EmpHibernateDAO implements EmpDAO_interface{
	
	
	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insert(EmpVO empVO) {
			hibernateTemplate.saveOrUpdate(empVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(EmpVO empVO) {
			hibernateTemplate.update(empVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Integer empid) {
		EmpVO empVO = new EmpVO();
		empVO.setEmpid(empid);
		hibernateTemplate.delete(empVO);
	}

	@Override
	public EmpVO findByPrimaryKey(Integer empid) {
		EmpVO empVO = (EmpVO)hibernateTemplate.get(EmpVO.class, empid);
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = (List<EmpVO>)hibernateTemplate.loadAll(EmpVO.class);
		return list;
	}

	@Override
	public Set<AnnouncementVO> getAnnouncementByEmpid(Integer empid) {
		Set<AnnouncementVO> set = findByPrimaryKey(empid).getAnnoucements();
		return set;
	}

}
