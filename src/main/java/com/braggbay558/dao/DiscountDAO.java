package com.braggbay558.dao;

import java.util.List;

import com.braggbay558.dao.GenericDAO;
import com.braggbay558.domain.Discount;





public interface DiscountDAO extends GenericDAO<Discount, Integer> {
  
	List<Discount> findAll();
	






}


