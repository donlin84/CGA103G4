package com.ClassIfm.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.ClassTag.model.ClassTagService;
import com.ClassTag.model.ClassTagVO;
import com.teacher.model.TeacherService;

public class ClassIfmVO implements java.io.Serializable{	
	private Integer claid;
	private Integer thrid;
	private Integer claTagid;
	private String claTitle;
	private String claIntroduction;
	private LocalDateTime claTime;
	private Integer claPrice;
	private Integer claPeopleMax;
	private Integer claPeopleMin;
	private Integer claPeople;
	private Integer claStatus;
	private LocalDateTime claStrTime;
	private LocalDateTime claFinTime;


	public Integer getClaid() {
		return claid;
	}
	public void setClaid(Integer claid) {
		this.claid = claid;
	}
	public Integer getThrid() {
		return thrid;
	}
	public void setThrid(Integer thrid) {
		this.thrid = thrid;
	}
	public Integer getClaTagid() {
		return claTagid;
	}
	public void setClaTagid(Integer claTagid) {
		this.claTagid = claTagid;
	}
	public String getClaTitle() {
		return claTitle;
	}
	public void setClaTitle(String claTitle) {
		this.claTitle = claTitle;
	}
	public String getClaIntroduction() {
		return claIntroduction;
	}
	public void setClaIntroduction(String claIntroduction) {
		this.claIntroduction = claIntroduction;
	}
	public LocalDateTime getClaTime() {
		return claTime;
	}
	public void setClaTime(LocalDateTime claTime) {
		this.claTime = claTime;
	}
	public Integer getClaPrice() {
		return claPrice;
	}
	public void setClaPrice(Integer claPrice) {
		this.claPrice = claPrice;
	}
	public Integer getClaPeopleMax() {
		return claPeopleMax;
	}
	public void setClaPeopleMax(Integer claPeopleMax) {
		this.claPeopleMax = claPeopleMax;
	}
	public Integer getClaPeopleMin() {
		return claPeopleMin;
	}
	public void setClaPeopleMin(Integer claPeopleMin) {
		this.claPeopleMin = claPeopleMin;
	}
	public Integer getClaPeople() {
		return claPeople;
	}
	public void setClaPeople(Integer claPeople) {
		this.claPeople = claPeople;
	}
	public Integer getClaStatus() {
		return claStatus;
	}
	public void setClaStatus(Integer claStatus) {
		this.claStatus = claStatus;
	}
	public LocalDateTime getClaStrTime() {
		return claStrTime;
	}
	public void setClaStrTime(LocalDateTime claStrTime) {
		this.claStrTime = claStrTime;
	}
	public LocalDateTime getClaFinTime() {
		return claFinTime;
	}
	public void setClaFinTime(LocalDateTime claFinTime) {
		this.claFinTime = claFinTime;
	}

	
	public com.teacher.model.TeacherVO getTeacherVO(){
		TeacherService teacherSrv = new TeacherService();
		com.teacher.model.TeacherVO teacherVO = teacherSrv.getOneTeacher(thrid);
		return teacherVO;
	}
	
	public com.ClassTag.model.ClassTagVO getClassTagVO(){
		ClassTagService classtagSrv = new ClassTagService();
		com.ClassTag.model.ClassTagVO classTagVO = classtagSrv.getOneClassTag(claTagid);
		return classTagVO;
	}

}

