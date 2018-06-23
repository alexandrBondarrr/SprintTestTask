package com.testTask.dao;

import com.testTask.model.Part;
import com.testTask.model.PartFilter;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Repository
@EnableTransactionManagement
@Transactional
public class PartDaoImpl implements PartDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Part part) {
        sessionFactory.getCurrentSession().save(part);
    }

    @Override
    public void update(Part part) {
        sessionFactory.getCurrentSession().update(part);
    }

    @Override
    public void delete(Part part) {
        sessionFactory.getCurrentSession().delete(part);
    }

    private Query query(String name) {
        return sessionFactory.getCurrentSession().getNamedQuery(name);
    }

    @Override
    public List<Part> list(PartFilter filter, String query) {

        List<Boolean> isRequired = null;
        switch(filter) {
            case ALL:
                isRequired = Arrays.asList(true, false);
                break;
            case ONLY_REQUIRED:
                isRequired = Arrays.asList(true);
                break;
            case ONLY_NOT_REQUIRED:
                isRequired = Arrays.asList(false);
                break;
        }

        return query("listAll")
                .setParameter("isRequired", isRequired)
                .setParameter("query", "%" + query + "%")
                .list();
    }

    @Override
    public Part byId(long id) {
        return (Part)query("getOneById").setParameter("id", id).getSingleResult();
    }


}
