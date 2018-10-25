package com.example.dao.impl;

import com.example.dao.SearchUserRepository;
import com.example.model.TblCompany;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.TblUser;

import javax.persistence.EntityManager;

@Repository
public class SearchUserRepositoryImpl implements SearchUserRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public List<TblUser> getListUsers(int companyId, String userFullName, String insuranceNumber, String register,
			String sort, int offset,
			int limit) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tbl_user AS tu INNER JOIN tbl_insurance AS ti ");
		sql.append("ON tu.insurance_internal_id=ti.insurance_internal_id ");
		sql.append("INNER JOIN tbl_company AS tc on tc.company_internal_id=tu.company_internal_id WHERE  1=1 ");
		if (companyId != 0) {
			sql.append("AND tc.company_internal_id =:companyId ");
		}
		if (userFullName != "") {
			sql.append("AND tu.user_full_name LIKE :userFullName ");
		}
		if (insuranceNumber != "") {
			sql.append(" AND ti.insurance_number LIKE :insuranceNumber");
		}
		if (register != "") {
			sql.append(" AND ti.place_of_register LIKE :register");
			
		}
		sql.append(" order by tu.user_full_name ").append(sort);
		Query query = (Query) entityManager.createNativeQuery(sql.toString(), TblUser.class);
		System.out.println("userFullName" + userFullName + "insuranceNumber" + insuranceNumber);
		if (companyId != 0) {
			query.setParameter("companyId", companyId);
		}
		if (userFullName != "") {
			query.setParameter("userFullName", "%" + userFullName + "%");
		}
		if (insuranceNumber != "") {
			query.setParameter("insuranceNumber", "%" + insuranceNumber + "%");
		}
		if (register != "") {
			query.setParameter("register", "%" + register + "%");
		}
		
		System.out.println("666666666666666666666" + userFullName + "insuranceNumber" + insuranceNumber
				+ "register" + register + "sort" + sort + "offset" + offset + "limit" + limit);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.getResultList();
		
	}
	
	@Override
	public Integer getTotalUser(String userFullName, String insuranceNumber, String register) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT COUNT(*) FROM tbl_user AS tu INNER JOIN tbl_insurance AS ti ON tu.insurance_internal_id=ti.insurance_internal_id WHERE 1=1 ");
		if (userFullName != "") {
			sql.append("AND tu.user_full_name LIKE :userFullName ");
		}
		if (insuranceNumber != "") {
			sql.append("AND ti.insurance_number LIKE :insuranceNumber");
		}
		if (register != "") {
			sql.append(" AND ti.place_of_register LIKE :register");
			
		}
		Query query = (Query) entityManager.createNativeQuery(sql.toString());
		if (userFullName != "") {
			query.setParameter("userFullName", "%" + userFullName + "%");
		}
		if (insuranceNumber != "") {
			query.setParameter("insuranceNumber", "%" + insuranceNumber + "%");
		}
		if (register != "") {
			query.setParameter("register", "%" + register + "%");
			
		}
		BigInteger totalUserLong = (BigInteger) (query.uniqueResult());
		return totalUserLong.intValue();
		
	}
	
	@Override
	public TblCompany getCompanyInforById(int companyId) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT tc.company_internal_id, tc.company_name, tc.address, tc.email, tc.telephone FROM tbl_company AS tc WHERE tc.company_internal_id=:companyId");
		Query query = (Query) entityManager.createNativeQuery(sql.toString(), TblCompany.class);
		if (companyId != 0) {
			query.setParameter("companyId", companyId);
		}
		return (TblCompany) query.getSingleResult();
	}
	
	@Override
	public void createUser(TblUser tblUser) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO tbl_user (company_internal_id, insurance_internal_id, username, password, user_full_name, user_sex_division, birthdate )");
		sql.append(
				"VALUES (:company_internal_id, :insurance_internal_id, :username, :password, :user_full_name, :user_sex_division, :birthdate)");
		javax.persistence.Query query = entityManager.createNativeQuery(sql.toString(), TblUser.class);
		query.setParameter("company_internal_id", tblUser.getTblCompany().getId());
		query.setParameter("insurance_internal_id", tblUser.getTblInsurance().getId());
		query.setParameter("username", tblUser.getUsername());
		query.setParameter("password", tblUser.getPassword());
		query.setParameter("user_full_name", tblUser.getUserFullName());
		query.setParameter("user_sex_division", tblUser.getUserSexDivision());
		query.setParameter("birthdate", tblUser.getDate());
		query.executeUpdate();
	}
}
