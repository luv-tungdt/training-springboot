package com.example.form;

public class UserForm {
    private String insuranceNumber;
    private String userFullName;
    private String userName;
    private String password;
    private String userSexDivision;
    private String birthDay;
    private String companyId;
    private String companyName;
    private String address;
    private String email;
    private String tel;
    private String register;
    private String startDate;
    private String endDate;
    private String flagCompany;

    public UserForm() { }
    public UserForm(String insuranceNumber, String userFullName, String userName, String password, String userSexDivision, String birthDay, String companyId, String companyName, String address, String email, String tel, String register, String startDate, String endDate, String flagCompany) {
        this.insuranceNumber = insuranceNumber;
        this.userFullName = userFullName;
        this.userName = userName;
        this.password = password;
        this.userSexDivision = userSexDivision;
        this.birthDay = birthDay;
        this.companyId = companyId;
        this.companyName = companyName;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.register = register;
        this.startDate = startDate;
        this.endDate = endDate;
        this.flagCompany = flagCompany;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserSexDivision() {
        return userSexDivision;
    }

    public void setUserSexDivision(String userSexDivision) {
        this.userSexDivision = userSexDivision;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFlagCompany() {
        return flagCompany;
    }

    public void setFlagCompany(String flagCompany) {
        this.flagCompany = flagCompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
