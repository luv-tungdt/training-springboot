package com.example.service.impl;

import com.example.dao.InsuranceRepository;
import com.example.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceImpl implements InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Override
    public boolean checkExistInsuranceNumber(String insuranceNumber) {
        if (insuranceRepository.findInsuranceNumber(insuranceNumber) == null) {
            return true;
        }
        return false;

    }
}
