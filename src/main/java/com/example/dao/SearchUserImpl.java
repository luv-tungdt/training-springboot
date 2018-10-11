package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.example.model.TblUser;

@Repository
public class SearchUserImpl implements SearchUserRepository {


	@Override
	public List<TblUser> search(String name) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        System.out.println("name servide" + name);
		StringBuilder sql = new StringBuilder();
		sql.append(" FROM TblUser WHERE TblUser.userFullName LIKE :name");
		Query query = session.createQuery(sql.toString());
		query.setParameter("name", "%" + name + "%");
		return (List<TblUser>) query.list();

	}
//    @Override
//    public List<TblUser> search(String userFullName, String numberInsurance, String register) {
//        Session session =sessionFactory.openSession();
//        List<TblUser> list =  session.createQuery("FROM TblUser WHERE TblUser.userFullName LIKE :userFullName")
//
}
