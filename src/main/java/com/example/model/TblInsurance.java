package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_insurance")
public class TblInsurance {
    @Id
    @Column(name = "insurance_internal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "insurance_number")
    private String numberInsurance;
    @Column(name = "insurance_start_date")
    private Date startDate;
    @Column(name = "insurance_end_date")
    private Date endDate;
    @Column(name = "place_of_register")
    private String register;
    @OneToMany(fetch = FetchType.LAZY,mappedBy ="tblInsurance")
    private Set<TblUser> listUser= new HashSet<>();

    public  TblInsurance(){

    }
    public TblInsurance(int id, String numberInsurance, Date startDate, Date endDate, String register, Set<TblUser> listUser) {
        this.id = id;
        this.numberInsurance = numberInsurance;
        this.startDate = startDate;
        this.endDate = endDate;
        this.register = register;
        this.listUser = listUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberInsurance() {
        return numberInsurance;
    }

    public void setNumberInsurance(String numberInsurance) {
        this.numberInsurance = numberInsurance;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public Set<TblUser> getListUser() {
        return listUser;
    }

    public void setListUser(Set<TblUser> listUser) {
        this.listUser = listUser;
    }
}
