package xyz.juandiii.jdbc;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class UserService {

    List<User> users;

    public UserService() {
        users = new ArrayList<>();
    }

    @Inject
    DataSource dataSource;


    public List<User> getUsers() throws SQLException  {
        try (Connection connection = dataSource.getConnection()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    users.add(new User().setId(UUID.fromString(resultSet.getString("id"))).setUsername(resultSet.getString("username")));
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;

    }


    public static final String SELECT_USERS = "SELECT * FROM users";

}
