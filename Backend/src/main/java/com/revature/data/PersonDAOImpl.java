package com.revature.data;

import com.revature.beans.Person;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import com.revature.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOImpl implements PersonDAO {
    private HibernateUtil hu = HibernateUtil.getHibernateUtil();
    @Override
    public Person getById(Integer id) {
        Session s = hu.getSession();
        Person p = s.get(Person.class, id);
        s.close();


//		CriteriaBuilder cb = s.getCriteriaBuilder();
//		CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
//		Root<Person> root = criteria.from(Person.class);
//
//		Predicate predicateForId = cb.equal(root.get("id"), id);
//
//		criteria.select(root).where(predicateForId);
//		Person p = s.createQuery(criteria).getSingleResult();
        return p;
    }

    @Override
    public List<Person> getAll() {
        Session s = hu.getSession();
        String query = "";
        query += "from Person";
        Query<Person> p = s.createQuery(query, Person.class);
        List<Person> personList = new ArrayList();
        personList = p.getResultList();

        s.close();

        return personList;
    }

    @Override
    public void update(Person person) {
        Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.update(person);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
            s.close();
        }
    }

    @Override
    public void delete(Person person) {

    }

    @Override
    public Person add(Person p) {
        Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.save(p);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
            s.close();
        }
        return p;
    }


    @Override
    public Person getByUsername(String username) {
        Session s = hu.getSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);

        Predicate predicateForUsername = cb.equal(root.get("username"), username);

        criteria.select(root).where(predicateForUsername);
        Person p = s.createQuery(criteria).getSingleResult();
        return p;
    }
}
