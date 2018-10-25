package com.example.dao.impl;

import java.util.List;

import com.example.dao.UserRepository;
import com.example.model.TblUser;

public abstract class UserRepositoryImpl implements UserRepository {
	
	@Override
	public List<TblUser> findAll() {
		return null;
	}

}
