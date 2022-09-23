package com.chefappointmentform.model;

import java.util.*;

public interface ChefAppointmentFormDAO_interface {
	public void insert(ChefAppointmentFormVO chefAppointmentFormVO);
    public void updateByChef(ChefAppointmentFormVO chefAppointmentFormVO);
    public void updateByMem(ChefAppointmentFormVO chefAppointmentFormVO);
    public ChefAppointmentFormVO findByapmid(Integer apmid);
    public List<ChefAppointmentFormVO> getAllByChef(Integer chefid);
    public List<ChefAppointmentFormVO> getAllByMem(Integer memid);
}
