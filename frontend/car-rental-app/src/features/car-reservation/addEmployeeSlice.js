import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  firstname: "",
  lastname: "",
  login: "",
  password: "",
  rePassword: "",
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
    loginChange: (state, action) => {
      state.login = action.payload;
    },
    passwordChange: (state, action) => {
      state.password = action.payload;
    },
    rePasswordChange: (state, action) => {
      state.rePassword = action.payload;
    },
  },
});

export const {
  firstnameChange,
  lastnameChange,
  loginChange,
  passwordChange,
  rePasswordChange,
} = addEmployeeSlice.actions;
export const selectAll = (state) => state.addEmployee;

export default addEmployeeSlice.reducer;
