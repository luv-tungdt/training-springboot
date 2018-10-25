package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.TblUser;
@Repository
public interface UserRepository extends JpaRepository<TblUser, Integer> {
	/**
	 * get all information of TblUser
	 *
	 * @return list user
	 */
	List<TblUser> findAll();



}
