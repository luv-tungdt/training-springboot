package com.example.service.impl;

import com.example.dao.CompanyRepository;
import com.example.dao.InsuranceRepository;
import com.example.dao.SearchUserRepository;
import com.example.form.UserForm;
import com.example.model.TblCompany;
import com.example.model.TblInsurance;
import com.example.model.TblUser;
import com.example.service.UserService;
import com.example.utils.Common;
import com.example.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private SearchUserRepository searchUserRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private InsuranceRepository insuranceRepository;
	
	
	@Override
	public List<TblUser> getListUsers(int companyId, String userFullName, String insuranceNumber, String register,
			String sort, int offset, int limit) {
		
		return searchUserRepository.getListUsers(companyId, userFullName, insuranceNumber, register, sort, offset,
				limit);
	}
	
	@Override
	public Integer getTotalUser(String userFullName, String insuranceNumber, String register) {
		
		return searchUserRepository.getTotalUser(userFullName, insuranceNumber, register);
	}
	
	@Override
	@Transactional
	public void createUser(UserForm userForm) throws ParseException {
		int companyId = Integer.parseInt(userForm.getCompanyId());

		String flagCompany = userForm.getFlagCompany();

		TblUser tblUser = new TblUser();
		TblCompany tblCompany = new TblCompany();
		TblInsurance tblInsurance = new TblInsurance();

		if (flagCompany.equals(Constant.FLAG_COMPANY)) {
			tblUser.setTblCompany(companyRepository.findCompanyById(companyId));
		} else {
			Common.setTblCompany(userForm, tblCompany);
			companyRepository.save(tblCompany);
			tblUser.setTblCompany(tblCompany);
			
		}
		Common.setTblUser(userForm, tblUser);
		Common.setTblInsurance(userForm, tblInsurance);
		
		insuranceRepository.save(tblInsurance);
		
		tblUser.setTblInsurance(tblInsurance);
		
		searchUserRepository.createUser(tblUser);
		
	}
	
}
