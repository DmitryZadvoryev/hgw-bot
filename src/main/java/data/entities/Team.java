package data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@NamedQueries({
        @NamedQuery(name = "team-list", query = "SELECT item FROM Team AS item"),
        @NamedQuery(name = "find-team-by-id", query = "SELECT item FROM Team AS item WHERE item.teamname = :team_name"),
        @NamedQuery(name = "sorted-team-list", query = "FROM Team item ORDER BY item.points DESC ")
})

@Entity
@Table(name = "teams")
public class Team implements Serializable {

    @Id
    @Column(name = "team_name")
    String teamname;

    @Column(columnDefinition = "bigint")
    Integer points;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    Collection<User> users;

    protected Team() {
    }

    public Team(String teamname) {
        this.teamname = teamname;
    }

    public Team(String teamname, Integer points) {
        this.teamname = teamname;
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public Collection<User> getMembers() {
        return users;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getTeamname() {
        return teamname;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Team)) {
            return false;
        }
        Team team = (Team) obj;
        return this.teamname.equals(team.teamname);
    }

    @Override
    public String toString() {
        return teamname + " " + points;
    }
}
