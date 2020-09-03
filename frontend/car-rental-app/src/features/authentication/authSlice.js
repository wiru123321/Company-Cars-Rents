import { createSlice } from "@reduxjs/toolkit";
import { handleLogin } from "../../services/LoginService";

const initialState = {
  success: false,
  failed: false,
  redirectTo: "",
  shouldRedirect: false,
  errorMessage: "",
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
    setFailed: (state, action) => {
      state.failed = action.payload;
    },
    setErrorMessage: (state, action) => {
      state.errorMessage = action.payload;
    },
  },
});

export const {
  setRedirectAddress,
  allowRedirect,
  setFailed,
  setErrorMessage,
} = authSlice.actions;

export const selectAll = (state) => state.auth;

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
      dispatch(setFailed(false));
      dispatch(setErrorMessage(""));
    } catch (error) {
      if (error.response) {
        if (error.response.status === 403) {
          dispatch(setFailed(true));
          dispatch(setErrorMessage("Your login or password is incorrect."));
        } else {
          dispatch(setFailed(true));
          dispatch(setErrorMessage("Unable to login."));
        }
      } else {
        dispatch(setFailed(true));
        dispatch(setErrorMessage("Cannot connect with server."));
      }
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
