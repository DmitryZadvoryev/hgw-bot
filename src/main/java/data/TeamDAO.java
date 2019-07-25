package data;

import data.entities.Team;
import java.sql.SQLException;
import java.util.List;

public interface TeamDAO {

    void cteate(Team team) throws SQLException;

    void update(Team team) throws SQLException;

    void delete(Team team) throws SQLException;

    Team findTeamByID(String teamName) throws SQLException;

    List<Team> sortedList() throws SQLException;

    List<Team> list() throws SQLException;
}
