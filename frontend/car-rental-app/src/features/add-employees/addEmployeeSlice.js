import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  firstname: "",
  lastname: "",
  email: "",
  login: "",
  phoneNumber: "",
  password: "",
  rePassword: "",
  role: "EMPLOYEE",
  didSubmit: false,
  success: false,
};

export const addEmployeeSlice = createSlice({
  name: "addEmployee",
  initialState,
  reducers: {
    firstnameChange: (state, action) => {
      state.firstname = action.payload;
    },

    lastnameChange: (state, action) => {
      state.lastname = action.payload;
    },

    emailChange: (state, action) => {
      state.email = action.payload;
    },

    loginChange: (state, action) => {
      state.login = action.payload;
    },

    phoneNumberChange: (state, action) => {
      state.phoneNumber = action.payload;
    },

    passwordChange: (state, action) => {
      state.password = action.payload;
    },

    rePasswordChange: (state, action) => {
      state.rePassword = action.payload;
    },

    roleChange: (state, action) => {
      state.role = action.payload;
    },

    toggleDidSubmit: (state, action) => {
      state.didSubmit = action.payload;
    },

    toggleSuccess: (state, action) => {
      state.success = action.payload;
    },

    reset: (state) => {
      state.firstname = "";
      state.lastname = "";
      state.email = "";
      state.login = "";
      state.phoneNumber = "";
      state.password = "";
      state.rePassword = "";
      state.didSubmit = false;
      state.success = false;
    },
  },
});

export const {
  firstnameChange,
  lastnameChange,
  emailChange,
  loginChange,
  phoneNumberChange,
  passwordChange,
  rePasswordChange,
  roleChange,
  toggleDidSubmit,
  toggleSuccess,
  reset,
} = addEmployeeSlice.actions;

export const selectAll = (state) => state.addEmployee;

export const selectAllUsers = (state) => state.addEmployee.users;

export const addUser = (userCration) => async (dispatch) => {
  try {
    const response = await axios.post(API_URL + "/a/user", userCration, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
  } catch (error) {
    console.log(error);
  }
};

export default addEmployeeSlice.reducer;
