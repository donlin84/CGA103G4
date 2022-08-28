package com.chef.model;

import java.util.List;

public class ChefService {
	private ChefDAO_interface dao;

	public ChefService() {
		dao = new ChefJDBCDAO();
	}

	public ChefVO addChef(String chefAccount, String chefPassword, String chefName,
			String chefNickname, Integer chefPrice, byte[] license, byte[] idCard, byte[] idCardBack, byte[] chefPhoto, String chefIntroduction) {

		ChefVO chefVO = new ChefVO();
		chefVO.setChefAccount(chefAccount);
		chefVO.setChefPassword(chefPassword);
		chefVO.setChefName(chefName);
		chefVO.setChefNickname(chefNickname);
		chefVO.setChefPrice(chefPrice);
		chefVO.setLicense(license);
		chefVO.setIdCard(idCard);
		chefVO.setIdCardBack(idCardBack);
		chefVO.setChefPhoto(chefPhoto);
		chefVO.setChefIntroduction(chefIntroduction);
		dao.insert(chefVO);

		return chefVO;
	}

	public ChefVO updateChef(Integer chefid, String chefName, String chefNickname, String chefAccount, String chefPassword, 
			Integer chefStatus, Integer chefPrice, byte[] license, byte[] idCard, byte[] idCardBack, byte[] chefPhoto, String chefIntroduction) {
//		public ChefVO updateChef(Integer chefid, String chefAccount, String chefPassword, Integer chefStatus, String chefName,
//				String chefNickname, Integer chefPrice, String schDate, Integer reserve, Integer com, Integer gomg,
//				byte[] license, byte[] idCard, byte[] idCardBack, byte[] chefPhoto, String chefIntroduction) {

		ChefVO chefVO = new ChefVO();
		chefVO.setChefid(chefid);
		chefVO.setChefName(chefName);
		chefVO.setChefNickname(chefNickname);
		chefVO.setChefAccount(chefAccount);
		chefVO.setChefPassword(chefPassword);
		chefVO.setChefStatus(chefStatus);
		chefVO.setChefPrice(chefPrice);
//		chefVO.setSchDate(schDate);
		chefVO.setLicense(license);
		chefVO.setIdCard(idCard);
		chefVO.setIdCardBack(idCardBack);
		chefVO.setChefPhoto(chefPhoto);
		chefVO.setChefIntroduction(chefIntroduction);
		dao.update(chefVO);

		return chefVO;
	}

	public ChefVO getOneChef(Integer chefid) {
//		System.out.println("testOne");
		return dao.findByPrimaryKey(chefid);
	}

	public List<ChefVO> getAll() {
//		System.out.println("testAll");
		return dao.getAll();
	}
}
