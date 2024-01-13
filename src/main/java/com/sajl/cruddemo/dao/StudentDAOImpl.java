package com.sajl.cruddemo.dao;

import com.sajl.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO
{
    public EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Student theStudent)
    {
        entityManager.persist( theStudent );
    }

    @Override
    public Student findById(Integer id)
    {
        return entityManager.find(Student.class , id );
    }

    @Override
    public List<Student> findAll()
    {
        TypedQuery<Student> theQuery = entityManager.createQuery( "From Student order by lastName asc" , Student.class );
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastname( String lastName )
    {
        TypedQuery<Student> theQuery = entityManager.createQuery( "From Student where lastName=:theData" , Student.class );
        theQuery.setParameter("theData" , lastName );
        return theQuery.getResultList();

    }

    @Override
    @Transactional
    public void update(Student theStudent)
    {
        entityManager.merge( theStudent );
    }

    @Override
    @Transactional
    public void delete(Integer id)
    {
        Student student = entityManager.find(Student.class , id );
        entityManager.remove( student );
    }

    @Override
    @Transactional
    public int deleteAll()
    {
        int noOfRows = entityManager.createQuery( "Delete From Student" , Student.class ).executeUpdate();
        return noOfRows;
    }
}
