package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CompanyRepository;
import com.example.model.TblCompany;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;


	@Override
	public List<TblCompany> findAll() {
		return companyRepository.findAll();
	}
	
}
