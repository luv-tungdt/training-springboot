package com.example.service;

import com.example.form.UserForm;
import com.example.model.TblCompany;
import com.example.model.TblUser;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface UserService {
    /**
     * Get information of User by following by conditions
     *
     * @param companyId Id company
     * @param userFullName
     * @param insuranceNumber
     * @param register
     * @param sort
     * @param offset
     * @param limit
     *
     * @return  list TblUser
     */
    List<TblUser> getListUsers(int companyId, String userFullName, String insuranceNumber, String register, String sort, int offset, int limit);
    /**
     * Total users by following conditions
     *
     * @param userFullName user full name
     * @param insuranceNumber insurance number
     * @param register place of register
     *
     * @return  list TblUser
     */
    Integer getTotalUser(String userFullName, String insuranceNumber, String register);
    /**
     * Add new user to the database
     *
     * @param userForm   object UserForm on view
     * @return TblCompany
     */
    void createUser(UserForm userForm) throws ParseException;



}
