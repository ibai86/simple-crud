package homework;

import homework.entity.User;
import homework.repository.UserDaoImpl;
import homework.utility.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;

@Slf4j
public class Main {
    public static void main(String[] args) {

        log.info("Hello!");
        UserDaoImpl userDao = new UserDaoImpl();
//        userDao.save(new User("John Smith", "mail@example.com", 33));

        while (true) {}
    }
}
