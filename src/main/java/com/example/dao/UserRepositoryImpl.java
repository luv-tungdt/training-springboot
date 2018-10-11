package com.example.dao;

import java.util.List;

import com.example.model.TblUser;

public  abstract class UserRepositoryImpl implements UserRepository{
    @Override
    public List<TblUser> findAll() {
        return null;
    }



    //@Override
//    public List<TblUser> search(int idCompany, String numberInsurance, String place) {
//        // SQL chinh query data bang cac param ben tren
//
//        return null;
//    }


    @Override
    public List<TblUser> findByUserFullName(String userFullName) {
        return null;
    }

    @Override
    public List<TblUser> findByUserFullNameAndInsuranceNumberAndregister(String userFullName, String insuranceNumber, String register) {
        return null;
    }


}
