package com.example.dao;

import com.example.model.TblUser;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SearchUserRepository {
	
	//List<TblUser> search(String userFullName, String numberInsurance, String register);
	List<TblUser> search(String userFullName,String insuranceNumber, String register);
	
}
