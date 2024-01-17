package com.example.sunbaseTest.customerManagement.RowMappers;

import com.example.sunbaseTest.customerManagement.Model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs,int rowNo) throws SQLException {
        Customer newCustomer = new Customer();
        newCustomer.setId(rs.getString("id"));
        newCustomer.setfName(rs.getString("fName"));
        newCustomer.setlName(rs.getString("lName"));
        newCustomer.setState(rs.getString("state"));
        newCustomer.setStreet(rs.getString("street"));
        newCustomer.setCity(rs.getString("city"));
        newCustomer.setAddress(rs.getString("address"));
        newCustomer.setEmail(rs.getString("email"));
        newCustomer.setPhone(rs.getString("phone"));
        return newCustomer;
    }
}
