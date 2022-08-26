package com.chefskills.model;

public class ChefSkillsVO implements java.io.Serializable{
	private Integer chefid;
	private Integer skillsid;
	
	public ChefSkillsVO() {
		
	}
	
	public ChefSkillsVO(Integer chefid, Integer skillsid) {
		super();
		this.chefid = chefid;
		this.skillsid = skillsid;
	}
	
	public Integer getSkillsid() {
		return skillsid;
	}
	public void setSkillsid(Integer skillsid) {
		this.skillsid = skillsid;
	}
	public Integer getChefid() {
		return chefid;
	}
	public void setChefid(Integer chefid) {
		this.chefid = chefid;
	}
	
}
