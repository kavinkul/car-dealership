/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.models.Role;
import com.sg.cardealership.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author agrah
 */
@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<User> getAllUsers() {
        final String SELECT_ALL_USERS = "SELECT * FROM user";
        return jdbc.query(SELECT_ALL_USERS, new UserMapper());
    }

    @Override
    public User getUser(String email) {
        try {
            final String SELECT_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
            return jdbc.queryForObject(SELECT_USER_BY_EMAIL, new UserMapper(), email);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public User addUser(User user) {
        final String INSERT_USER = "INSERT INTO user(email, firstName, "
                 + "lastName, passwordHash, role) VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_USER,
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPasswordHash(),
                user.getRole().getValue());

        //String newEmail = jdbc.queryForObject("SELECT LAST_INSERT_ID()", String.class);
        //user.setEmail(newEmail);
        return user;
    }

    @Override
    public void removeUser(String email) {
        final String DELETE_USER = "DELETE FROM user WHERE email = ?";
        jdbc.update(DELETE_USER, email);
    }

    @Override
    public void editUser(User user) {
        final String UPDATE_USER = "UPDATE user SET firstName = ?, lastName = ? "
                + "WHERE email = ?";
        jdbc.update(UPDATE_USER,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
    }

    public static final class UserMapper implements RowMapper<User> {

        private String emailField;
        private String firstNameField;
        private String lastNameField;
        private String passwordHashField;
        private String roleField;

        public UserMapper() {
            this.emailField = "email";
            this.firstNameField = "firstName";
            this.lastNameField = "lastName";
            this.passwordHashField = "passwordHash";
            this.roleField = "role";
        }

        public UserMapper(String emailField, String firstNameField, String lastNameField, String passwordHashField, String roleField) {
            this.emailField = emailField;
            this.firstNameField = firstNameField;
            this.lastNameField = lastNameField;
            this.passwordHashField = passwordHashField;
            this.roleField = roleField;
        }

        @Override
        public User mapRow(ResultSet rs, int index) throws SQLException {
            User user = new User();
            user.setEmail(rs.getString(this.emailField));
            user.setFirstName(rs.getString(this.firstNameField));
            user.setLastName(rs.getString(this.lastNameField));
            user.setPasswordHash(rs.getString(this.passwordHashField));
            if(rs.getString(this.roleField).equalsIgnoreCase("admin")){
                user.setRole(Role.ADMIN);
            }
            else if(rs.getString(this.roleField).equalsIgnoreCase("sales")){
                user.setRole(Role.SALES);
            }
            return user;
        }
    }

}
