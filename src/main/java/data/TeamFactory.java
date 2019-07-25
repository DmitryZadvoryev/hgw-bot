package data;

import data.entities.Team;

import java.sql.SQLException;
import java.util.List;

import static validation.Validation.requireIsEmpty;

public class TeamFactory {

    private static TeamFactory instance = null;

    public static synchronized TeamFactory getInstance() {
        if (instance == null) {
            instance = new TeamFactory();
        }
        return instance;
    }

    public void getTeams() {
        try {
            List<Team> list = Factory.getInstance().getTeamDAO().list();
            if (requireIsEmpty(list)) {
                Team gryffindor = new Team("Gryffindor", 0);
                Team hufflepuff = new Team("Hufflepuff", 0);
                Team ravenclaw = new Team("Ravenclaw", 0);
                Team slytherin = new Team("Slytherin", 0);
                Factory.getInstance().getTeamDAO().cteate(gryffindor);
                Factory.getInstance().getTeamDAO().cteate(hufflepuff);
                Factory.getInstance().getTeamDAO().cteate(ravenclaw);
                Factory.getInstance().getTeamDAO().cteate(slytherin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
