package data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "list-users", query = "SELECT item FROM User AS item"),
        @NamedQuery(name = "find-user-by-id", query = "SELECT item FROM User AS item WHERE item.id = :id")
})

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String userID;

    @Column(name = "name")
    private String name;

    @Column(name = "timer")
    private LocalTime time;

    @Column(name = "pts", columnDefinition = "bigint")
    private Integer points;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_name")
    private Team team;

    public User() {
    }

    public User(String userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    public User(String userID,String name, Team team) {
        this.userID = userID;
        this.name = name;
        this.team = team;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(String teamName) {
        this.team = new Team(teamName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(userID, user.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\''  +
                ", name='" + name + '\'' +
                ", points=" + points +
                ", team=" + team +
                '}';
    }
}
