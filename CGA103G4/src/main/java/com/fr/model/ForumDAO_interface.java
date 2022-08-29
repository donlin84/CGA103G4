package com.fr.model;

import java.util.List;

public interface ForumDAO_interface {
	public void insert(ForumVO forumVO);

	public void update(ForumVO forumVO);

	public void delete(Integer frid);

	public ForumVO findByPrimaryKey(Integer frid);

	public List<ForumVO> getAll();
}
