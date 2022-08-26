package com.chefskillstype.model;

public class ChefSkillsTypeVO implements java.io.Serializable{
	private Integer skillid;
	private String skill;
	
	public ChefSkillsTypeVO() {
		
	}
	
	public ChefSkillsTypeVO(Integer skillid, String skill) {
		super();
		this.skillid = skillid;
		this.skill = skill;
	}
	
	public Integer getSkillid() {
		return skillid;
	}
	public void setSkillid(Integer skillid) {
		this.skillid = skillid;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	
}
