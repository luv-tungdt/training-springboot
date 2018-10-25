package com.example.dao;

import com.example.model.TblInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InsuranceRepository extends JpaRepository<TblInsurance,Integer> {

    /**
     * Find insurance number
     *
     * @param insuranceNumber insurance number
     * @return an object insurance
     */
    @Query(value = "SELECT * FROM tbl_insurance ins WHERE ins.insurance_number = ?1", nativeQuery = true)
    TblInsurance findInsuranceNumber(String insuranceNumber);
}
