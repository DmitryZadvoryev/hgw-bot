package data;

import data.entities.Team;
import util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class TeamDAOImpl implements TeamDAO {

    private Session session;

    public TeamDAOImpl() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public void cteate(Team team) throws SQLException {
        session.beginTransaction();
        session.save(team);
        session.getTransaction().commit();
    }

    @Override
    public void update(Team team) throws SQLException {
        session.beginTransaction();
        session.update(team);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Team team) throws SQLException {
        session.beginTransaction();
        session.delete(team);
        session.getTransaction().commit();
    }

    @Override
    public Team findTeamByID(String teamName) throws SQLException {
        try {
            return session.createNamedQuery("find-team-by-id", Team.class).setParameter("team_name", teamName).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Team> sortedList() throws SQLException {
        try {
            return session.createNamedQuery("sorted-team-list", Team.class).getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Team> list() throws SQLException {
        try {
            return session.createNamedQuery("team-list", Team.class).getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }

}
