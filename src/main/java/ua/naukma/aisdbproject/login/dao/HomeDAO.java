package ua.naukma.aisdbproject.login.dao;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import ua.naukma.aisdbproject.login.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Component
public class HomeDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HomeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getByCredentials(User user) {
        List<User> storedUser = jdbcTemplate.query("SELECT * FROM `users` WHERE usr_username=?",
                new Object[]{user.getUsrUsername()},
                new BeanPropertyRowMapper<>(User.class));
        if (!storedUser.isEmpty() && BCrypt.checkpw(user.getUsrPassword(), storedUser.get(0).getUsrPassword()))
            return storedUser.get(0);
        else
            return null; // No user found or incorrect password
    }
}