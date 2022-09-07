package com.chefsubscription.model;

import java.util.List;
import java.util.Map;



public class ChefSubscriptionService {
	private ChefSubscriptionDAO_interface dao;

	public ChefSubscriptionService() {
		dao = new ChefSubscriptionJDBCDAO();
	}

	public ChefSubscriptionVO addChefSubscription(Integer chefid, Integer memid) {

		ChefSubscriptionVO chefSubscriptionVO = new ChefSubscriptionVO();

		chefSubscriptionVO.setChefid(chefid);
		chefSubscriptionVO.setMemid(memid);
		dao.insert(chefSubscriptionVO);

		return chefSubscriptionVO;
	}
	
	public void deleteChefSubscription(Integer chefid, Integer memid) {
		dao.delete(chefid, memid);
	}


	public ChefSubscriptionVO getOneChefSubscription(Integer chefid, Integer memid) {
		return dao.findByPrimaryKey(chefid, memid);
	}

	public List<ChefSubscriptionVO> getAll() {
		return dao.getAll();
	}
	
	public List<ChefSubscriptionVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
