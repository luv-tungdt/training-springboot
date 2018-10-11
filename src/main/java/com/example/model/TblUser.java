package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tbl_user")
public class TblUser {
    @Id
    @Column(name = "user_internal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "company_internal_id", nullable = false)
    private TblCompany tblCompany;
    @ManyToOne
    @JoinColumn(name = "insurance_internal_id", nullable = false)
    private TblInsurance tblInsurance;
    @Column(name = "username",length = 15, nullable = false)
    private String username;
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    @Column(name = "user_full_name", length = 50, nullable = false)
    private String userFullName;
    @Column(name = "user_sex_division", length = 2, nullable = false)
    private char[] userSexDivision;
    @Column(name = "birthdate")
    private Date date;
    public TblUser(){

    }

    public TblUser(int id, TblCompany tblCompany, TblInsurance tblInsurance, String username, String password, String userFullName, char[] userSexDivision, Date date) {
        this.id = id;
        this.tblCompany = tblCompany;
        this.tblInsurance = tblInsurance;
        this.username = username;
        this.password = password;
        this.userFullName = userFullName;
        this.userSexDivision = userSexDivision;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TblCompany getTblCompany() {
        return tblCompany;
    }

    public void setTblCompany(TblCompany tblCompany) {
        this.tblCompany = tblCompany;
    }

    public TblInsurance getTblInsurance() {
        return tblInsurance;
    }

    public void setTblInsurance(TblInsurance tblInsurance) {
        this.tblInsurance = tblInsurance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserSexDivision() {
        String sex = String.valueOf(userSexDivision);
        if(sex.equals("01")){
            sex = "Nam";
        }else{
            sex = "Nữ";
        }
        return sex;
    }

    public void setUserSexDivision(char[] userSexDivision) {
        this.userSexDivision = userSexDivision;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
