package com.example.sunbaseTest.customerManagement.Repository;

import com.example.sunbaseTest.customerManagement.Model.Customer;
import com.example.sunbaseTest.customerManagement.RowMappers.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private boolean findIfAlreadyExist(Customer customerBody)
    {
        String query = "select * from customers where id='"+customerBody.getId()+"' ;";
        List<Customer> filteredCustomers = jdbcTemplate.query(query,new CustomerRowMapper());
        if(filteredCustomers.size()>0) return true;
        return false;
    }

    public int addCustomer(Customer customerBody)
    {
        String query = "insert into customers (id,fName,lName,address,street,city,state,email,phone)" +
                " values (?,?,?,?,?,?,?,?,?);";
        if(this.findIfAlreadyExist(customerBody)) {
            return 1;
        }
        int rowsAffected = jdbcTemplate.update(query, customerBody.getId(),customerBody.getfName(),
                customerBody.getlName(),customerBody.getAddress(),customerBody.getStreet(),customerBody.getCity(),
                customerBody.getState(),customerBody.getEmail(),customerBody.getPhone());
        return rowsAffected;
    }

    public List<Customer> getAllCustomer()
    {
        String query = "select * from customers LIMIT 10 OFFSET 0;";
        List<Customer> customers = jdbcTemplate.query(query,new CustomerRowMapper());
        return customers;
    }

    public List<Customer> getFilteredCustomer(int page,int limit,String searchCriteria,String searchText)
    {
        String offset = String.valueOf((page-1)*limit);
        String query = "select * from customers where "+searchCriteria+" LIKE '%"+searchText+"%' LIMIT "+limit+" OFFSET "+offset+";";
        List<Customer> filteredCustomers = jdbcTemplate.query(query,new CustomerRowMapper());
        return filteredCustomers;
    }

    public int updateCustomer(Customer customer)
    {
        String query = "UPDATE customers set fName='"+customer.getfName()+"', lName='"+customer.getlName()+"', address='"+customer.getAddress()
                +"', street='"+customer.getStreet()+"', state='"+customer.getState()+"', city='"+customer.getCity()+"', phone='"+customer.getPhone()
                +"', email='"+customer.getEmail()+"' where id='"+customer.getId()+"';";
        System.out.println(query);
        int rowsAffected = jdbcTemplate.update(query);
        return rowsAffected;
    }

    public int deleteCustomer(String id)
    {
        String query = "delete from customers where id='"+id+"' ;";
        int a = jdbcTemplate.update(query);
        return a;
    }
}

