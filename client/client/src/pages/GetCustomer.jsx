import { useScroll } from 'framer-motion';
import React, { useEffect, useState } from 'react';
import TextField from '@mui/material/TextField';
import { FormControl,Select,MenuItem, TableContainer,Table,TableHead,TableRow,TableBody,TableCell,Button } from '@mui/material';
import Paper from '@mui/material/Paper';
import axiosInstance from './utils/axiosInstance';
import { Navigate, useNavigate } from 'react-router-dom';
import axios from 'axios';

function GetCustomer() {

    const [page,setPage] = useState(1);
    const [limit,setLimit] = useState(10);

    const [searchCriteria,setSearchCriteria] = useState("fName");
    const [searchText,setSearchText] = useState("");
    const [rowData,setRowData] = useState("");
    const navigate = useNavigate();

    useEffect(()=>{
        const token = localStorage.getItem('token');
        axiosInstance.defaults.headers.common["Authorization"] = token;
        const url ="/customer/getFilteredCustomers?page="+page+"&limit="+limit+"&searchCriteria="+searchCriteria+"&searchText="+searchText;
        const fetchData = async () => {
        try{
            const response = await axiosInstance.get(url);
            setRowData(response.data);
            console.log(rowData[0].fName);
        }
        catch(e)
        {
            console.log(e);
        }}
        fetchData();
    },[page,limit,searchText,searchCriteria]);

    function updateHandle(id)
    {
        navigate("/updateCustomer/"+id)
    }

    async function deleteHandler(id)
    {
        try{
            const response = await axiosInstance.delete("/customer/deleteCustomer?id="+id);
            window.location.reload();
        }
        catch(e)
        {
            console.log(e);
        }
    }

    function handleChange(event){
        setSearchCriteria(event.target.value);
    }

    async function syncHandler(){
        axiosInstance.get("/customer/sync");
        window.location.reload();
    }

  return (
    <div>
        <div style={{width:"500px",margin:"auto auto"}}>
            <h1>All Customers</h1>
            <TextField value={page} onChange={(e)=>setPage(e.target.value)}  placeholder='Page No' size='small' variant='outlined'/>
            <TextField value={limit} onChange={(e)=>setLimit(e.target.value)} placeholder='Limit' size='small' variant='outlined'/>
            <FormControl sx={{ m: 1, minWidth: 120 }}>
                <Select size='small'
                    value={searchCriteria}
                    onChange={handleChange}
                    displayEmpty
                    inputProps={{ 'aria-label': 'Without label' }}
                    >
                    <MenuItem value={"fName"}>First Name</MenuItem>
                    <MenuItem value={"lName"}>Last Name</MenuItem>
                    <MenuItem value={"email"}>Email</MenuItem>
                    <MenuItem value={"phone"}>Phone</MenuItem>
                </Select>
            </FormControl>
            <TextField value={searchText} onChange={(e)=>setSearchText(e.target.value)} sx={{marginTop:'8px'}} placeholder='Search Text' size='small' variant='outlined'/>
        </div>
      <div style={{width:"max-content",maxWidth:"80%" ,margin:"auto auto"}}>
        <TableContainer component={Paper}>
                <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>First Name</TableCell>
                        <TableCell align="right">Last Name</TableCell>
                        <TableCell align="right">Street</TableCell>
                        <TableCell align="right">Address</TableCell>
                        <TableCell align="right">City</TableCell>
                        <TableCell align="right">State</TableCell>
                        <TableCell align="right">Phone</TableCell>
                        <TableCell align="right">Email</TableCell>
                        <TableCell align="right">Update</TableCell>
                        <TableCell align="right">Delete</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rowData && rowData.map((row)=>{
                        return (<TableRow key={row.id}
                                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                            <TableCell>{row.fName}</TableCell>
                            <TableCell>{row.lName}</TableCell>
                            <TableCell>{row.street}</TableCell>
                            <TableCell>{row.address}</TableCell>
                            <TableCell>{row.city}</TableCell>
                            <TableCell>{row.state}</TableCell>
                            <TableCell>{row.phone}</TableCell>
                            <TableCell>{row.email}</TableCell>
                            <TableCell>
                                <Button variant='contained' color='success' onClick={()=>updateHandle(row.id)}>Update</Button>
                            </TableCell>
                            <TableCell>
                                <Button variant='contained' color='warning' onClick={()=>deleteHandler(row.id)}>Delete</Button>
                            </TableCell>
                        </TableRow>)
                    })}
                </TableBody>

                </Table>
            </TableContainer>
      </div>
      <Button sx={{marginBottom:5,marginTop:5}} variant='contained' onClick={syncHandler}>Sync</Button>
    </div>
  )
}

export default GetCustomer