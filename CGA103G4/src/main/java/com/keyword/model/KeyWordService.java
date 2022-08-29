package com.keyword.model;

import java.util.List;

public class KeyWordService {
	private KeyWordDAO_interface dao;

	public KeyWordService() {
		dao = new KeyWordJDBCDAO();
	}
	public KeyWordVO addKeyWord(String kwName, String kwContext) {

		KeyWordVO keyWordVO = new KeyWordVO();

		keyWordVO.setKwName(kwName);
		keyWordVO.setKwContext(kwContext);
		dao.insert(keyWordVO);
		return keyWordVO;
	}
	public KeyWordVO updateKeyWord(String kwName, String kwContext,Integer kwid) {

			KeyWordVO keyWordVO = new KeyWordVO();

			keyWordVO.setKwName(kwName);
			keyWordVO.setKwContext(kwContext);
			keyWordVO.setKwid(kwid);
			dao.update(keyWordVO);

			return keyWordVO;
	}		
		
	public void deleteKeyWord(Integer kwid) {
		dao.delete(kwid);
	}

	public KeyWordVO getOneKeyWord(Integer kwid) {
		return dao.findByPrimaryKey(kwid);
	}

	public List<KeyWordVO> getAll() {
		return dao.getAll();
	}		
		
		
			
}
