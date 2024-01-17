package com.example.sunbaseTest.customerManagement.Controller;

import com.example.sunbaseTest.customerManagement.Model.Customer;
import com.example.sunbaseTest.customerManagement.Repository.CustomerRepository;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RemoteServerController remoteServerController;

    @GetMapping("/getFilteredCustomers")
    public ResponseEntity<List<Customer>> getAllCustomer(@RequestParam int page,@RequestParam int limit,
                                                         @RequestParam String searchCriteria,@RequestParam String searchText){
        return new ResponseEntity<>(customerRepository.getFilteredCustomer(page,limit,searchCriteria,searchText), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        return new ResponseEntity<>(customerRepository.getAllCustomer(), HttpStatus.OK);
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer)
    {
        System.out.println("generating");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        customer.setId(id);
        int a = customerRepository.addCustomer(customer);
        return new ResponseEntity<>("Customer created Successfully",HttpStatus.CREATED);
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<Integer> updateCustomer(@RequestBody Customer customer)
    {
        System.out.println("updating");
        return new ResponseEntity<>(customerRepository.updateCustomer(customer),HttpStatus.OK);
    }

    @DeleteMapping("/deleteCustomer")
//    public ResponseEntity<String> deleteCustomer(@RequestBody Customer customer)
    public ResponseEntity<String> deleteCustomer(@RequestParam String id)
    {
        int a = customerRepository.deleteCustomer(id);
        if(a==1)
            return new ResponseEntity<>("delete successfully",HttpStatus.OK);
        else return new ResponseEntity<>("Some error occured, check server logs",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/sync")
    public ResponseEntity<List<Customer>> SyncData(HttpServletRequest request)
    {
        String jwtToken = request.getHeader("Authorization").substring(7);
        List<Customer> remoteServerData = remoteServerController.doWebSearch("url",jwtToken);
        for(Customer c : remoteServerData)
        {
            c.setfName(c.getFirst_name());
            c.setlName(c.getLast_name());
            c.setId(c.getUuid());
            customerRepository.addCustomer(c);
        }
        return new ResponseEntity<>(customerRepository.getAllCustomer(), HttpStatus.OK);
    }
}
