import React from 'react';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';

function Home() {

    const navigate = useNavigate();

    function navigateToAddCustomerPage(){
        navigate('/addCustomer');
    }

    function navigateToGetCustomerPage(){
        navigate('/getCustomer');
    }

    function navigateToLoginPage(){
        navigate('/login');
    }

  return (
    <div>
        <Button variant='contained' size='small' color='success' sx={{display:'block',margin:'auto auto'}} onClick={navigateToAddCustomerPage}>Add Customer Page</Button>
        <Button variant='contained' size='small' color='success' sx={{display:'block',margin:'auto auto'}} onClick={navigateToGetCustomerPage}>Search Customer Page</Button>
        <Button variant='contained' size='small' color='success' sx={{display:'block',margin:'auto auto'}} onClick={navigateToLoginPage}>Login Page</Button>
    </div>
  )
};

export default Home