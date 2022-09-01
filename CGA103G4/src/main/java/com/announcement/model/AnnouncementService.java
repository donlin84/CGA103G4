package com.announcement.model;

import java.sql.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emp.model.EmpVO;

public class AnnouncementService {
	
	private static AnnouncementDAO_interface dao;
	
	static {
		//dao = new EmpHibernateDAO();
		//註1: 雖然model-config1-DriverManagerDataSource.xml也可以用
		//註2: 但為了使用Apache DBCP連線池,以提高效能,所以底下的model-config2-JndiObjectFactoryBean.xml內部dataSource設定是採用org.springframework.jndi.JndiObjectFactoryBean
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config2-JndiObjectFactoryBean.xml");
		dao =(AnnouncementDAO_interface) context.getBean("AnnouncementHibernateDAO");
	}

	public AnnouncementVO addAnnouncement(Integer empid , String annContent, byte[] annPic, Integer annStatus,
			Date annUpdate, Date annTime) {
		
		AnnouncementVO announcementVO = new AnnouncementVO();
		
		EmpVO empVO = new EmpVO();
		empVO.setEmpid(empid);
		announcementVO.setEmpVO(empVO);
		announcementVO.setAnnContent(annContent);
		announcementVO.setAnnPic(annPic);
		announcementVO.setAnnStatus(annStatus);
		announcementVO.setAnnUpdate(annUpdate);
		announcementVO.setAnnTime(annTime);
		
		dao.insert(announcementVO);
		
		return announcementVO;
		
	}
	
	//預留給 Struts 2 或 Spring MVC 用
	public void addAnnouncement(AnnouncementVO announcementVO) {
		dao.insert(announcementVO);
	}
	
	public AnnouncementVO updateAnnouncement(Integer annid, Integer empid, String annContent, byte[] annPic, Integer annStatus,
			Date annUpdate, Date annTime) {
		
		AnnouncementVO announcementVO = new AnnouncementVO();
		
		EmpVO empVO = new EmpVO();
		empVO.setEmpid(empid);
		announcementVO.setEmpVO(empVO);
		announcementVO.setAnnid(annid);
		announcementVO.setAnnContent(annContent);
		announcementVO.setAnnPic(annPic);
		announcementVO.setAnnStatus(annStatus);
		announcementVO.setAnnUpdate(annUpdate);
		announcementVO.setAnnTime(annTime);
		
		dao.update(announcementVO);
		
		return announcementVO;
	}
	
	public void updateAnnouncement(AnnouncementVO announcementVO) {
		dao.update(announcementVO);
	}
	public void deleteAnnouncement(Integer annid) {
		dao.delete(annid);
	}
	public AnnouncementVO getOneAnnouncement(Integer annid) {
		return dao.findByPrimaryKey(annid);
	}
	public List<AnnouncementVO> getAll() {
		return dao.getAll();
	}

}
