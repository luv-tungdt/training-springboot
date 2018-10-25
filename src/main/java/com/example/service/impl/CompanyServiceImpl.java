package com.example.service.impl;

import java.util.List;

import com.example.service.CompanyService;
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

    @Override
    public TblCompany findCompanyById(int companyId) {
        return companyRepository.findCompanyById(companyId);
    }
}
