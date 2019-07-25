package data;

import data.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void cteate(User user) throws SQLException;

    void update(User user) throws SQLException;

    void delete(User user) throws SQLException;

    User get(String id) throws SQLException;

    List<User> list() throws SQLException;
}
