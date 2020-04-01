package data;

import data.entities.User;
import util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UserDAOImpl implements data.UserDAO {

    private Session session;

    public UserDAOImpl() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    @Override
    public void cteate(User user) throws SQLException {
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void update(User user) throws SQLException {
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
    }

    @Override
    public void delete(User user) throws SQLException {
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public User get(String id) throws SQLException {
        try {
            return session.createNamedQuery("find-user-by-id", User.class).setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> list() throws SQLException {
        try {
            return session.createNamedQuery("list-users", User.class).getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }
}
