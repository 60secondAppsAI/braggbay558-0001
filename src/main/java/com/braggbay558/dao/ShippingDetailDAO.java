package com.braggbay558.dao;

import java.util.List;

import com.braggbay558.dao.GenericDAO;
import com.braggbay558.domain.ShippingDetail;





public interface ShippingDetailDAO extends GenericDAO<ShippingDetail, Integer> {
  
	List<ShippingDetail> findAll();
	






}


