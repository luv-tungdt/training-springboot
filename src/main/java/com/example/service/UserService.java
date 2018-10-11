package com.example.service;

import com.example.model.TblUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
	
	List<TblUser> findAll();
	
	//List<TblUser> search(int idCompany, String numberInsurance, String place);
	List<TblUser> findByUserFullName(String userFullName);
	
	List<TblUser> findByUserFullNameAndInsuranceNumberAndregister(String userFullName, String insuranceNumber,
			String register);
	
	List<TblUser> search(String userFullName, String insuranceNumber, String register);
	
}
