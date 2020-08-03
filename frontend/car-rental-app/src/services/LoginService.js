import axios from "axios";

const API_URL = "http://localhost:8080/login";

export const handleLogin = ({ login, password }) => {
  return axios.post(API_URL, {
    login: login,
    password: password,
  });
};

const saveToken = (token) => {
  localStorage.setItem("token", token);
};

const handleRemove = () => {
  localStorage.removeItem("token");
  localStorage.removeItem("role");
};
