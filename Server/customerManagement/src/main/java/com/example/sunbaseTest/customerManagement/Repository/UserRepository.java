package com.example.sunbaseTest.customerManagement.Repository;

import com.example.sunbaseTest.customerManagement.Model.UserLoginCredentials;
import com.example.sunbaseTest.customerManagement.RowMappers.UserLoginCredentialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String userLogin(UserLoginCredentials user)
    {
        String query = "select * from usercredential where login_id='"+user.getLogin_id()+"' ;";
        List<UserLoginCredentials> fetchedUser = jdbcTemplate.query(query,new UserLoginCredentialMapper());
        if(fetchedUser.get(0).getPass().equals(user.getPass()))
        {
            return "access_token";
        }
        else return "unauthorized";
    }

    public UserLoginCredentials findByUsername(String username)
    {
        String query = "select * from usercredential where login_id='"+username+"';";
        List<UserLoginCredentials> user = jdbcTemplate.query(query,new UserLoginCredentialMapper());
        return user.get(0);
    }

    public UserDetails getUserByEmail(String userEmail) {
        String query = "select * from usercredential where login_id='"+userEmail+"';";
        List<UserLoginCredentials> user = jdbcTemplate.query(query,new UserLoginCredentialMapper());
        return user.get(0);
    }
}
