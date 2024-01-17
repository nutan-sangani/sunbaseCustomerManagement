package com.example.sunbaseTest.customerManagement.Controller;

import com.example.sunbaseTest.customerManagement.Model.AccessToken;
import com.example.sunbaseTest.customerManagement.Model.Customer;
import com.example.sunbaseTest.customerManagement.Model.UserLoginCredentials;
import com.example.sunbaseTest.customerManagement.Repository.UserDetailService;
import com.example.sunbaseTest.customerManagement.Repository.UserRepository;
import com.example.sunbaseTest.customerManagement.Utils.JwtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemoteServerController {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    private final RestTemplate restTemplate= new RestTemplate();
    public List<Customer> doWebSearch(String url,String jwtToken){
        HttpHeaders headers = new HttpHeaders();
        try{
            AccessToken token = getAuthToken("https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp",jwtToken);
            headers.set("Authorization","Bearer "+token);
            String uri = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";

            HttpEntity entity = new HttpEntity<>(headers);

            ResponseEntity<List<Customer>> response = restTemplate.exchange(uri, HttpMethod.GET,entity, new ParameterizedTypeReference<List<Customer>>() {});
            List<Customer> resBody = response.getBody();
            return resBody;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return new ArrayList<Customer>();
    }

    public AccessToken getAuthToken(String url,String jwtToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String userName = jwtService.extractUserName(jwtToken);
        UserLoginCredentials user = userRepository.findByUsername(userName);

        MultiValueMap<String,String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("login_id",user.getLogin_id());
        requestBody.add("password",user.getPassword());

        HttpEntity<MultiValueMap<String,String>> requestEntity = new HttpEntity<>(requestBody,headers);

        ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.POST,requestEntity,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        AccessToken token = objectMapper.readValue(response.getBody(),AccessToken.class);
        return token;
    }
}





