package com.braggbay558.dao;

import java.util.List;

import com.braggbay558.dao.GenericDAO;
import com.braggbay558.domain.User;

import java.util.Optional;




public interface UserDAO extends GenericDAO<User, Integer> {
  
	List<User> findAll();
	






}


