package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.TblUser;
@Repository
public interface UserRepository extends JpaRepository<TblUser, Integer> {
	
	List<TblUser> findAll();
	
	@Query("SELECT t From TblUser t WHERE t.userFullName=?1 AND t.tblInsurance.numberInsurance=?2 AND t.tblInsurance.register=?3")
	List<TblUser>findByUserFullNameAndInsuranceNumberAndregister(String userFullName,String insuranceNumber,String register);


	@Query("SELECT  t from TblUser t where t.userFullName = ?1")
	List<TblUser> findByUserFullName(String userFullName);


}
