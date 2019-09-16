package bot;

import data.Factory;
import data.entities.Team;
import data.entities.User;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.Constants.*;
import static constants.Constants.SLYTHERIN;

public class BotService {

    public static Map<String, Team> convertListToMap(List<Team> list) throws SQLException {

        Map<String, Team> map = new HashMap<>();
        for (Team team : list) {
            map.put(team.getTeamname(), team);
        }
        return map;
    }

    public String getList(List<Team> list) {

        StringBuffer userList = new StringBuffer();

        for (Team item : list) {
            if (item.getTeamname().equalsIgnoreCase(GRYFFINDOR)) {
                userList.append("Гриффиндор: ");
            }
            if (item.getTeamname().equalsIgnoreCase(HUFFLEPUFF)) {
                userList.append("Пуффендуй: ");
            }
            if (item.getTeamname().equalsIgnoreCase(RAVENCLAW)) {
                userList.append("Когтевран: ");
            }
            if (item.getTeamname().equalsIgnoreCase(SLYTHERIN)) {
                userList.append("Слизерин: ");
            }
            Collection<User> members = item.getMembers();
            for (User member : members) {
                userList.append(member.getName() + ", ");
            }
            if (userList.charAt(userList.length() - 2) == ',') {
                userList.setCharAt(userList.length() - 2, ' ');
            }
            userList.append("\n");
        }
        return userList.toString();
    }

    public String getTeamPoints(List<Team> list) {
        StringBuilder points = new StringBuilder();
        for (Team item : list) {
            if (item.getTeamname().equalsIgnoreCase(GRYFFINDOR)) {
                points.append("Гриффиндор: " + item.getPoints() + " очков" + "\n");
            }
            if (item.getTeamname().equalsIgnoreCase(HUFFLEPUFF)) {
                points.append("Пуффендуй: " + item.getPoints() + " очков" + "\n");
            }
            if (item.getTeamname().equalsIgnoreCase(RAVENCLAW)) {
                points.append("Когтевран: " + item.getPoints() + " очков" + "\n");
            }
            if (item.getTeamname().equalsIgnoreCase(SLYTHERIN)) {
                points.append("Слизерин: " + item.getPoints() + " очков" + "\n");
            }
        }
        return points.toString();
    }

    public String userAlreadyInDatabase(User user) {
        StringBuilder message = new StringBuilder();
        switch (user.getTeam().getTeamname()) {
            case (GRYFFINDOR):
                message.append("Ты уже в Гриффиндоре");
                break;
            case (HUFFLEPUFF):
                message.append("Ты уже в Пуффендуе");
                break;
            case (RAVENCLAW):
                message.append("Ты уже в Когтевране");
                break;
            case (SLYTHERIN):
                message.append("Ты уже в Слизерине");
                break;
        }
        return message.toString();
    }

    public String rollTeam(User user, int randomValue, List<Team> teamList) throws SQLException {
        Map<String, Team> stringTeamMap = BotService.convertListToMap(teamList);
        StringBuilder message = new StringBuilder();
        switch (randomValue) {
            case (1):
                user.setTeam(stringTeamMap.get(GRYFFINDOR).getTeamname());
                Factory.getInstance().getUserDAO().cteate(user);
                message.append("Гриффиндор!");
                break;
            case (2):
                user.setTeam(stringTeamMap.get(HUFFLEPUFF).getTeamname());
                Factory.getInstance().getUserDAO().cteate(user);
                message.append("Пуффендуй!");
                break;
            case (3):
                user.setTeam(stringTeamMap.get(RAVENCLAW).getTeamname());
                Factory.getInstance().getUserDAO().cteate(user);
                message.append("Когтевран!");
                break;
            case (4):
                user.setTeam(stringTeamMap.get(SLYTHERIN).getTeamname());
                Factory.getInstance().getUserDAO().cteate(user);
                message.append("Слизерин!");
                break;
        }
        return message.toString();
    }

    public String catchSnitch(User user, List<Team> teamList) throws SQLException {

        Map<String, Team> stringTeamMap = BotService.convertListToMap(teamList);

        StringBuilder message = new StringBuilder();

        if (user.getTeam().equals(stringTeamMap.get(GRYFFINDOR))) {
            Team gr = Factory.getInstance().getTeamDAO().findTeamByID(GRYFFINDOR);
            gr.setPoints(gr.getPoints() + POINTS);
            Factory.getInstance().getTeamDAO().update(gr);

            message.append("Сегодня вы оказались самым ловким и везучим, снитч ваш, а с ним и победа для вашей комманды! \n+ " + POINTS + " очков Гриффиндору");

        } else if (user.getTeam().equals(stringTeamMap.get(HUFFLEPUFF))) {
            Team pu = Factory.getInstance().getTeamDAO().findTeamByID(HUFFLEPUFF);
            pu.setPoints(pu.getPoints() + POINTS);
            Factory.getInstance().getTeamDAO().update(pu);
            message.append("Сегодня вы оказались самым ловким и везучим, снитч ваш, а с ним и победа для вашей комманды! \n+ " + POINTS + " очков Пуффендую");

        } else if (user.getTeam().equals(stringTeamMap.get(RAVENCLAW))) {
            Team rw = Factory.getInstance().getTeamDAO().findTeamByID(RAVENCLAW);
            rw.setPoints(rw.getPoints() + POINTS);
            Factory.getInstance().getTeamDAO().update(rw);
            message.append("Сегодня вы оказались самым ловким и везучим, снитч ваш, а с ним и победа для вашей комманды! \n+ " + POINTS + " очков Когтеврану");

        } else {
            Team sl = Factory.getInstance().getTeamDAO().findTeamByID(SLYTHERIN);
            sl.setPoints(sl.getPoints() + POINTS);
            Factory.getInstance().getTeamDAO().update(sl);
            message.append("Сегодня вы оказались самым ловким и везучим, снитч ваш, а с ним и победа для вашей комманды! \n+ " + POINTS + " очков Слизерину");
        }
        return message.toString();
    }
}
