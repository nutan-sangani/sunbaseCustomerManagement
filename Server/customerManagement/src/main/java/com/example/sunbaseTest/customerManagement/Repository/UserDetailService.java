package com.example.sunbaseTest.customerManagement.Repository;

import com.example.sunbaseTest.customerManagement.Model.UserLoginCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.getUserByEmail(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("user does not exist");
        }
        return user;
    }
}
