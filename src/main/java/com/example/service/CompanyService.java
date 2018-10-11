package com.example.service;

import com.example.model.TblCompany;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CompanyService {
	
	List<TblCompany> findAll();
	
}
