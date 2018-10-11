package com.example.service;

import com.example.dao.SearchUserRepository;
import com.example.dao.UserRepository;
import com.example.model.TblUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("UserService")
public  class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SearchUserRepository searchUser;
	
	
	@Override
	public List<TblUser> findAll() {
		return userRepository.findAll();
	}
	
//    @Override
//    public List<TblUser> search(int idCompany, String numberInsurance, String place) {
//        return null;
//    }
	
	@Override
	public List<TblUser> findByUserFullName(String userFullName) {
		return userRepository.findByUserFullName(userFullName);
	}
	
	@Override
	public List<TblUser> findByUserFullNameAndInsuranceNumberAndregister(String userFullName, String insuranceNumber,
			String register) {
		return userRepository.findByUserFullNameAndInsuranceNumberAndregister(userFullName, insuranceNumber, register);
	}
	
	@Override
	public List<TblUser> search(String userFullName) {
		System.out.println("userFullName"+ userFullName);
		return searchUser.search(userFullName);
	}


//	@Override
//	public List<TblUser> search(String userFullName) {
//		return searchUser.search(userFullName);
//	}
}
