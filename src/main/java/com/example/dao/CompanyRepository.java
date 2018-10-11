package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.TblCompany;

@Repository
public interface CompanyRepository extends JpaRepository<TblCompany,Integer> {

}
