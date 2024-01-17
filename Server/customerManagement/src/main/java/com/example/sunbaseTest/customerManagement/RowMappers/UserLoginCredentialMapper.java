package com.example.sunbaseTest.customerManagement.RowMappers;

import com.example.sunbaseTest.customerManagement.Model.UserLoginCredentials;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginCredentialMapper implements RowMapper<UserLoginCredentials> {

    public UserLoginCredentials mapRow(ResultSet rs,int rowNo) throws SQLException
    {
        UserLoginCredentials userCredentials = new UserLoginCredentials();
        userCredentials.setLogin_id(rs.getString("login_id"));
        userCredentials.setPass(rs.getString("pass"));
        return userCredentials;
    }
}
