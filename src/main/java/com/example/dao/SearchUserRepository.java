package com.example.dao;

import com.example.model.TblCompany;
import com.example.model.TblUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchUserRepository {
    /**
     * Find all user with condition search
     *
     * @param companyId       companyId
     * @param userFullName    userFullName
     * @param insuranceNumber insuranceNumber
     * @param register        place of register
     * @param sort            sort  of user full name
     * @param limit           limit of user
     * @param offset          index of start user in database
     * @return list user
     */
    List<TblUser> getListUsers(int companyId, String userFullName, String insuranceNumber, String register, String sort, int offset, int limit);

    /**
     * Total users by following conditions
     *
     * @param userFullName    userFullName
     * @param insuranceNumber insuranceNumber
     * @param register        place of register
     *
     * @return total User
     */
    Integer getTotalUser(String userFullName, String insuranceNumber, String register);
    /**
     * get company information by Id
     *
     * @param companyId   companyId
     *
     * @return TblCompany
     */
    TblCompany getCompanyInforById(int companyId);
    /**
     * Add new user to the database
     *
     * @param tblUser   object tblUser
     * @return TblCompany
     */
    void createUser(TblUser tblUser);

}
