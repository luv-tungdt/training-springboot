package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.TblCompany;

@Repository
public interface CompanyRepository extends JpaRepository<TblCompany,Integer> {
    @Query(value = "SELECT * FROM tbl_company c WHERE c.company_internal_id = ?1", nativeQuery = true)
    TblCompany findCompanyById(int companyId);

}
