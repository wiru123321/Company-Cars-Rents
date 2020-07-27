import { createSlice } from "@reduxjs/toolkit";
import { act } from "react-dom/test-utils";

const initialState = {
  firstname: "",
  lastname: "",
  email: "",
  login: "",
  phoneNumber: "",
  password: "",
  rePassword: "",
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
  toggleDidSubmit,
  toggleSuccess,
  reset,
} = addEmployeeSlice.actions;

export const selectAll = (state) => state.addEmployee;

export default addEmployeeSlice.reducer;
