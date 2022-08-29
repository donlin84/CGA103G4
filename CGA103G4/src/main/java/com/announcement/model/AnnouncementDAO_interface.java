package com.announcement.model;

import java.util.List;

public interface AnnouncementDAO_interface {
    public void insert(AnnouncementVO announcementVO);
    public void update(AnnouncementVO announcementVO);
    public void delete(Integer annid);
    public AnnouncementVO findByPrimaryKey(Integer annid);
    public List<AnnouncementVO> getAll();
}
