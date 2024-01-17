import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import { Button } from '@mui/material';
import axiosInstance from './utils/axiosInstance';
import { useNavigate, useParams } from 'react-router-dom';

function UpdateCustomer() {
    const [fn,setFN] = useState("");
    const [ln,setLN] = useState("");
    const [street,setStreet] = useState("");
    const [addr,setAddr] = useState("");
    const [email,setEmail] = useState("");
    const [state,setState] = useState("");
    const [phone,setPhone] = useState("");
    const [city,setCity] =  useState("");
    const navigate = useNavigate();
    const {id} = useParams();

    function submitHandler(event)
    {
        event.preventDefault();
        const token = localStorage.getItem('token');
        axiosInstance.defaults.headers.common["Authorization"] =token;
        const body={id:id,fName:fn,lName:ln,address:addr,city:city,state:state,email:email,phone:phone,street:street};
        axiosInstance.put('/customer/updateCustomer',body)
                     .then((res)=>{
                        alert("customer successfully updated");
                        navigate("/getCustomer");
                     })
                     .catch((e)=>{
                        console.log(e);
                        alert("some error occured, check console for details");
                     })
    }

  return (
    <div style={{width:'500px',margin:'auto auto'}}>
        <h1>Update Customer</h1>
        <form onSubmit={submitHandler}>
            <TextField size='small' required="true" onChange={(e)=>setFN(e.target.value)} value={fn} variant='outlined' placeholder='First Name'/>
            <TextField size='small' required="true" onChange={(e)=>setLN(e.target.value)} value={ln} variant='outlined' placeholder='last Name'/>
            <TextField size='small' required="true" onChange={(e)=>setStreet(e.target.value)} value={street} variant='outlined' placeholder='Street'/>
            <TextField size='small' required="true" onChange={(e)=>setAddr(e.target.value)} value={addr} variant='outlined' placeholder='Address'/>
            <TextField size='small' required="true" onChange={(e)=>setCity(e.target.value)} value={city} variant='outlined' placeholder='City'/>
            <TextField size='small' required="true" onChange={(e)=>setState(e.target.value)} value={state} variant='outlined' placeholder='State'/>
            <TextField size='small' required="true" onChange={(e)=>setEmail(e.target.value)} value={email} variant='outlined' placeholder='Email'/>
            <TextField size='small' required="true" onChange={(e)=>setPhone(e.target.value)} value={phone} variant='outlined' placeholder='Phone'/>
            <Button size='small' variant='contained' color='success' type='submit'>Update</Button>
        </form>
    </div>
  )
}

export default UpdateCustomer;