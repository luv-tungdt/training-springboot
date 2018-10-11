package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.TblUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

@Repository
public class SearchUserImpl implements SearchUserRepository {
	
//	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public List<TblUser> search(String name, String insuranceNumber, String register) {
//		Session session = sessionFactory.openSession();
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT * FROM tbl_user AS t ,tbl_insurance AS i , tbl_company AS c WHERE t.user_full_name LIKE :name ");

		if (insuranceNumber != null) {
			sql.append("AND i.insurance_number LIKE :insuranceNumber");
		}
		if (register != null) {
			sql.append(" AND i.place_of_register LIKE :regiser");
			
		}
		Query query = (Query) entityManager.createNativeQuery(sql.toString(), TblUser.class);
		query.setParameter("name", "%" + name + "%");
		query.setParameter("insuranceNumber","%" + insuranceNumber +" %");
		query.setParameter("register","%" + register + "%");

		return query.getResultList();
		
	}
//    @Override
//    public List<TblUser> search(String userFullName, String numberInsurance, String register) {
//        Session session =sessionFactory.openSession();
//        List<TblUser> list =  session.createQuery("FROM TblUser WHERE TblUser.userFullName LIKE :userFullName")
//
}
