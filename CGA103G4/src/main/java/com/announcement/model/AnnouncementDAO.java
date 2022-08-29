package com.announcement.model;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class AnnouncementDAO implements AnnouncementDAO_interface{
	
	
	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insert(AnnouncementVO announcementVO) {
			hibernateTemplate.saveOrUpdate(announcementVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(AnnouncementVO announcementVO) {
			hibernateTemplate.update(announcementVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Integer annid) {
		AnnouncementVO announcementVO = new AnnouncementVO();
		announcementVO.setAnnid(annid);
		hibernateTemplate.delete(announcementVO);
	}

	@Override
	public AnnouncementVO findByPrimaryKey(Integer annid) {
		AnnouncementVO announcementVO = (AnnouncementVO)hibernateTemplate.get(AnnouncementVO.class, annid);
		return announcementVO;
	}

	@Override
	public List<AnnouncementVO> getAll() {
		List<AnnouncementVO> list = (List<AnnouncementVO>)hibernateTemplate.loadAll(AnnouncementVO.class);
		return list;
	}

}
