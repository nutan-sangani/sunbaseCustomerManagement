import React from "react";
import {useState} from 'react';
import axios from 'axios';
import './css/Login.css';
import  axiosInstance  from "./utils/axiosInstance";
import { useNavigate } from "react-router-dom";

function Login() {
  const [login_id, setLoginId] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  function loginIdHandler(event) {
    setLoginId(event.target.value);
  }

  function passwordChangeHandler(event) {
    setPassword(event.target.value);
  }

  function submitHandler(event) {
    event.preventDefault();
    const body = {login_id:login_id,pass:password};
    axiosInstance
      .post("/auth/login", body)
      .then((res) => {
        console.log(res.data);
        localStorage.setItem("token","Bearer "+res.data.access_token);
        alert("successfully logged in, redirecting to home page");
        navigate("/");
      })
      .catch((err) => {
        alert("check credetials or check if server is up");
      });
  }

  return (
    <div className="App">
    <h1>Login Page</h1>
      <div>
        <form className="LoginForm">
          <label>Login Id</label>
          <input type="text" value={login_id} onChange={loginIdHandler}></input>
          <label>Password</label>
          <input
            type="password"
            value={password}
            onChange={passwordChangeHandler}
          ></input>
          <button type="submit" onClick={submitHandler}>
            submit
          </button>
        </form>
      </div>
    </div>
  );
}

export default Login;
