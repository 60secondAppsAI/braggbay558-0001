package com.braggbay558.dao;

import java.util.List;

import com.braggbay558.dao.GenericDAO;
import com.braggbay558.domain.Message;





public interface MessageDAO extends GenericDAO<Message, Integer> {
  
	List<Message> findAll();
	






}


