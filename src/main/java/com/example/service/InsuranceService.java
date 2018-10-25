package com.example.service;

import org.springframework.stereotype.Service;

@Service
public interface InsuranceService {
    /**
     * Check insurance number exist or not
     *
     * @param insuranceNumber insurance number
     * @return true if insurance number not exist and false if insurance number existed
     */
    boolean checkExistInsuranceNumber(String insuranceNumber);
}
