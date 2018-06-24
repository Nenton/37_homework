package ru.innopolis.stc9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.dao.connection.TestDaoJdbc;
import ru.innopolis.stc9.hibernate.Test;
import ru.innopolis.stc9.hibernate.TestDao;

import java.sql.Date;
import java.util.Random;
import java.util.UUID;

@Controller
public class IndexController {
    private final TestDao testDao;
    private final TestDaoJdbc daoJdbc;

    @Autowired
    public IndexController(TestDao testDao, TestDaoJdbc daoJdbc) {
        this.testDao = testDao;
        this.daoJdbc = daoJdbc;
    }

    @RequestMapping(value = "/hibernate", method = RequestMethod.GET)
    public String getHibernate(Model model) {
        long time = System.currentTimeMillis();
//        testDao.startSession();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Test test = new Test(random.nextInt(999999999),
                    ((double) random.nextInt(999999999)),
                    new Date(random.nextInt(999999999)));
            testDao.saveTest(test);
        }
//        testDao.closeSession();
        time = System.currentTimeMillis() - time;
        model.addAttribute("time", time);
        model.addAttribute("method", "Hibernate");
        return "hibernate";
    }

    @RequestMapping(value = "/jdbc", method = RequestMethod.GET)
    public String getJdbc(Model model) {
        long time = System.currentTimeMillis();

        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Test test = new Test(random.nextInt(999999999),
                    ((double) random.nextInt(999999999)),
                    new Date(random.nextInt(999999999)));
            test.setId(UUID.randomUUID().toString());
            daoJdbc.saveTest(test);
        }
        time = System.currentTimeMillis() - time;
        model.addAttribute("time", time);
        model.addAttribute("method", "JDBC");
        return "hibernate";
    }

    @RequestMapping(value = "/readjdbc", method = RequestMethod.GET)
    public String readjdbc(Model model) {
        long time = System.currentTimeMillis();
        int size = daoJdbc.readJdbc().size();
        time = System.currentTimeMillis() - time;
        model.addAttribute("time", time);
        model.addAttribute("method", "JDBC");
        return "hibernate";
    }

    @RequestMapping(value = "/readhibernate", method = RequestMethod.GET)
    public String readHibernate(Model model) {
        long time = System.currentTimeMillis();
        int size = testDao.read().size();
        time = System.currentTimeMillis() - time;
        model.addAttribute("time", time);
        model.addAttribute("method", "Hibernate");
        return "hibernate";
    }
}
