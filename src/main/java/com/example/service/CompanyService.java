package com.example.service;

import com.example.model.TblCompany;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompanyService {
	/**
	 * get all information of TblCompany
	 *
	 * @return list company
	 */
	List<TblCompany> findAll();
	/**
	 * Get information of company by Id
	 *
	 * @param companyId Id company
	 *
	 * @return  TblCompany
	 */
	TblCompany findCompanyById(int companyId);


}
