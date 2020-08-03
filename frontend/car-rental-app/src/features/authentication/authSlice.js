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
  reducers: {
    logInAsAdmin: (state) => {
      state.loggedStatus = logInStatus.ADMIN;
    },

    logInAsEmployee: (state) => {
      state.loggedStatus = logInStatus.EMPLOYEE;
    },

    logout: (state) => {
      state.loggedStatus = logInStatus.NONE;
    },

    saveUser: (action) => {
      localStorage.setItem("user", action.payload);
    },

    removeToken: () => {
      localStorage.removeItem("token");
    },
  },
});

export const {
  logInAsEmployee,
  logInAsAdmin,
  saveUser,
  removeToken,
  logout,
} = authSlice.actions;

export const selectLoggedInStatus = (state) => state.auth.loggedStatus;

export const login = ({ login, password }) => {
  return async (dispatch) => {
    try {
      const response = await handleLogin({ login, password });
      console.log(response.data);
      const { token, role } = response.data;
      const user = { token: token, username: login, role: role };
      //dispatch(saveUser(user));
      localStorage.setItem("token", token);
      localStorage.setItem("role", role);

      if (role === logInStatus.ADMIN) {
        dispatch(logInAsAdmin());
      } else if (role === logInStatus.EMPLOYEE) {
        dispatch(logInAsEmployee());
      } else {
        dispatch(logout());
      }
    } catch (error) {
      console.log(error);
    }
  };
};

export default authSlice.reducer;
