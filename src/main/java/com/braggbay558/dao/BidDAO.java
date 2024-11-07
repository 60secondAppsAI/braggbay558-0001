package com.braggbay558.dao;

import java.util.List;

import com.braggbay558.dao.GenericDAO;
import com.braggbay558.domain.Bid;





public interface BidDAO extends GenericDAO<Bid, Integer> {
  
	List<Bid> findAll();
	






}


