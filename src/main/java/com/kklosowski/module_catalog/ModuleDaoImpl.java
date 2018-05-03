package com.kklosowski.module_catalog;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * The type Module dao.
 */
public class ModuleDaoImpl implements ModuleDao {

    /**
     * The hibernate session.
     */
    Session session = null;
    /**
     * The hibernate transaction.
     */
    Transaction tx = null;

    @Autowired
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public Module getModule(String name) {
        return (Module) sessionFactory
                .openSession()
                .createQuery("from Module m where m.name = :name")
                .setParameter("name", name)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Module> getAllModules(Boolean discontinued) {
        return (List<Module>) sessionFactory
                .openSession()
                .createQuery("from Module m where m.discontinued = :discontinued")
                .setParameter("discontinued", discontinued)
                .list();
    }

    @Override
    public List<Module> getModulesBySubject(String subject, Boolean discontinued) {
        return (List<Module>) sessionFactory
                .openSession()
                .createQuery("from Module m where m.subject = :subject and m.discontinued = :discontinued")
                .setParameter("subject", subject)
                .setParameter("discontinued", discontinued)
                .list();
    }

    @Override
    public List<Module> getModulesBySubjectAndLevel(String subject, int level, Boolean discontinued) {
        return (List<Module>) sessionFactory
                .openSession()
                .createQuery("from Module m where m.subject = :subject and m.level = :level " +
                        "and m.discontinued = :discontinued")
                .setParameter("subject", subject)
                .setParameter("level", level)
                .setParameter("discontinued", discontinued)
                .list();
    }

    @Override
    public boolean addModule(Module module) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        if (getModule(module.getName()) != null) {
            return false;
        } else {
            session.save(module);
            tx.commit();
            return true;
        }
    }

    @Override
    public boolean deleteModule(String name) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        Module module = getModule(name);

        if (module != null) {
            session.delete(module);
            tx.commit();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateModule(String name, Boolean discontinued) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        Module module = getModule(name);
        if (module != null) {
            module.setDiscontinued(discontinued);
            session.update(module);
            tx.commit();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<String> getSubjects() {
        session = sessionFactory.openSession();
        return session.createNativeQuery("SELECT DISTINCT m.subject FROM Module m").list();
    }

    @Override
    public List<Integer> getLevels() {
        session = sessionFactory.openSession();
        return session.createNativeQuery("SELECT DISTINCT m.level FROM Module m WHERE m.subject == subject").list();
    }
}
