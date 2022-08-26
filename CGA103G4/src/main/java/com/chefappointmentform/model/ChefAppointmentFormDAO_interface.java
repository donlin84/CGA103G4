package com.chefappointmentform.model;

import java.util.*;

public interface ChefAppointmentFormDAO_interface {
	public void insert(ChefAppointmentFormVO chefAppointmentFormVO);
    public void update(ChefAppointmentFormVO chefAppointmentFormVO);
    public ChefAppointmentFormVO findByPrimaryKey(Integer apmid);
    public List<ChefAppointmentFormVO> getAll();
}
