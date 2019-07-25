package data;

public class Factory {

    private static Factory instance = null;
    private static TeamDAO teamDAO = null;
    private static UserDAO userDAO = null;

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public TeamDAO getTeamDAO() {
        if (teamDAO == null) {
            teamDAO = new TeamDAOImpl();
        }
        return teamDAO;
    }

    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }
}
