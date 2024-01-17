import axios from "axios";

const axiosInstance = axios.create({
    baseURL:"http://localhost:8080",
});

const token = localStorage.getItem('token');
axiosInstance.defaults.headers.common["Authorization"] = token;

export default axiosInstance;