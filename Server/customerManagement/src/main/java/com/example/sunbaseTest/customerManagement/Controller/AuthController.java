package com.example.sunbaseTest.customerManagement.Controller;

import com.example.sunbaseTest.customerManagement.Model.AccessToken;
import com.example.sunbaseTest.customerManagement.Model.UserLoginCredentials;
import com.example.sunbaseTest.customerManagement.Repository.UserRepository;
import com.example.sunbaseTest.customerManagement.Utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AccessToken> userLogin(@RequestBody UserLoginCredentials user)
    {
        String response = userRepository.userLogin(user);
        if(response.equals("unauthorized"))
        {
            AccessToken token =new AccessToken();
            token.setAccess_token("unauthorized");
            return new ResponseEntity<>(token,HttpStatus.UNAUTHORIZED);
        }
        AccessToken token = jwtService.generateToken(user);
        return ResponseEntity.ok(token);
    }

}
