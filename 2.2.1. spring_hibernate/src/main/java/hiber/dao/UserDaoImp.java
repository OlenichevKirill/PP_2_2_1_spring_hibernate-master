package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession().createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public Optional<User> getUserByModelAndSeries(String model, int series) {
        String hql = "select u from User u where u.car.model = :model and u.car.series =:series";
        return sessionFactory.getCurrentSession().createQuery(hql, User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .uniqueResultOptional();

    }
}
