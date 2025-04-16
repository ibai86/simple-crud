package homework.utility;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class HibernateUtil {

    @Getter
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(homework.entity.User.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            log.error("Initial SessionFactory creation failed cause {}", ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }
}
