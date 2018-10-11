package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_company")
public class TblCompany {
    @Id
    @Column(name = "company_internal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "company_name",length = 50,nullable = false)
    private String name;
    @Column(name = "address",length = 100, nullable = false)
    private String address;
    @Column(name = "email",length = 50)
    private String email;
    @Column(name = "telephone",length = 15)
    private String tel;
    @OneToMany(fetch = FetchType.LAZY,mappedBy ="tblCompany")
    private Set<TblUser> listUser= new HashSet<>();

    public TblCompany(){

    }

    public TblCompany(int id, String name, String address, String email, String tel, Set<TblUser> listUser) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.listUser = listUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<TblUser> getListUser() {
        return listUser;
    }

    public void setListUser(Set<TblUser> listUser) {
        this.listUser = listUser;
    }
}
