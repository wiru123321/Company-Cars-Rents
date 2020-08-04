import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { handleLogin } from "../../services/LoginService";

const initialState = {
  success: false,
  failed: false,
  redirectTo: "",
  shouldRedirect: false,
};

export const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    setRedirectAddress: (state, action) => {
      state.redirectTo = action.payload;
    },

    allowRedirect: (state, action) => {
      state.shouldRedirect = action.payload;
    },
  },
});

export const { setRedirectAddress, allowRedirect } = authSlice.actions;

export const selectLoggedInStatus = (state) => state.auth.loggedStatus;

export const selectRedirectAddress = (state) => state.auth.redirectTo;

export const selectShouldRedirect = (state) => state.auth.shouldRedirect;

export const login = ({ login, password }) => {
  return async (dispatch) => {
    try {
      const response = await handleLogin({ login, password });
      const { token, role } = response.data;
      localStorage.setItem("token", token);
      localStorage.setItem("role", role);
      if (role === "ADMIN") {
        dispatch(setRedirectAddress("/adminPage"));
      } else if (role === "EMPLOYEE") {
        dispatch(setRedirectAddress("/userPage"));
      }
      dispatch(allowRedirect(true));
    } catch (error) {
      console.log(error);
    }
  };
};

export const logout = () => {
  return (dispatch) => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    dispatch(setRedirectAddress("/login"));
    dispatch(allowRedirect(false));
  };
};

export default authSlice.reducer;
