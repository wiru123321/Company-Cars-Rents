import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { handleLogin } from "../../services/LoginService";

const Role = { username: "", role: "" };

const logInStatus = { NONE: "NONE", ADMIN: "ADMIN", EMPLOYEE: "EMPLOYEE" };
const initialState = {
  loggedStatus: logInStatus.NONE,
};

export const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {},
});

export const {} = authSlice.actions;

export const selectLoggedInStatus = (state) => state.auth.loggedStatus;

export const login = ({ login, password }) => {
  return async (dispatch) => {
    try {
      const response = await handleLogin({ login, password });
      const { token, role } = response.data;
      localStorage.setItem("token", token);
      localStorage.setItem("role", role);
    } catch (error) {
      console.log(error);
    }
  };
};

export const logout = () => {
  localStorage.removeItem("token");
  localStorage.removeItem("role");
};

export default authSlice.reducer;
