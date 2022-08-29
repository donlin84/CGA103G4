package com.chefskills.model;

public class ChefSkillsVO implements java.io.Serializable{
	private Integer chefid;
	private Integer skillid;
	
	public ChefSkillsVO() {
		
	}
	
	public ChefSkillsVO(Integer chefid, Integer skillid) {
		super();
		this.chefid = chefid;
		this.skillid = skillid;
	}
	
	public Integer getSkillid() {
		return skillid;
	}
	public void setSkillid(Integer skillid) {
		this.skillid = skillid;
	}
	public Integer getChefid() {
		return chefid;
	}
	public void setChefid(Integer chefid) {
		this.chefid = chefid;
	}
	
	public com.chef.model.ChefVO getChefVO() {
	    com.chef.model.ChefService chefSvc = new com.chef.model.ChefService();
	    com.chef.model.ChefVO chefVO = chefSvc.getOneChef(chefid);
	    return chefVO;
    }
	
}
