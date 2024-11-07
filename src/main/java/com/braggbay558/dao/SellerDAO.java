package com.braggbay558.dao;

import java.util.List;

import com.braggbay558.dao.GenericDAO;
import com.braggbay558.domain.Seller;





public interface SellerDAO extends GenericDAO<Seller, Integer> {
  
	List<Seller> findAll();
	






}


