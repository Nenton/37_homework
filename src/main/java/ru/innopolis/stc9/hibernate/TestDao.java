package ru.innopolis.stc9.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDao {
    private final SessionFactory factory;

    @Autowired
    public TestDao(SessionFactory factory) {
        this.factory = factory;
    }


    public void saveTest(Test test) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(test);
        session.getTransaction().commit();
        session.close();
    }

    public List<Test> read() {
        Session session = factory.openSession();
        return   session.createQuery("from Test").list();
    }
}
