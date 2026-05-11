package lk.ijse.serenitymentalhealththerapycenter.config;

import lk.ijse.serenitymentalhealththerapycenter.entity.Patient;
import lk.ijse.serenitymentalhealththerapycenter.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;

    private SessionFactory sessionFactory;

    private FactoryConfiguration() {

//        hibernate bootstrapping and schema strategies
        Configuration configuration = new Configuration();

        configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(Therapist.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return factoryConfiguration == null ?
                factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}
