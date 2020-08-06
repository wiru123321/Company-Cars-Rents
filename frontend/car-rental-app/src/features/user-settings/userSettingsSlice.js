import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  changeFormId: 0,
  password: "",
  newPassword: "",
  newEmail: "",
  newPhoneNumber: "",
};

export const userSettingsSlice = createSlice({
  name: "userSettings",
  initialState,
  reducers: {
    toggleForm: (state, action) => {
      state.changeFormId = action.payload;
    },

    setPassword: (state, action) => {
      state.password = action.payload;
    },
    setNewPassword: (state, action) => {
      state.newPassword = action.payload;
    },
    setNewEmail: (state, action) => {
      state.newEmail = action.payload;
    },
    setNewPhoneNumber: (state, action) => {
      state.newPhoneNumber = action.payload;
    },
  },
});

export const {
  toggleForm,
  setPassword,
  setNewPassword,
  setNewEmail,
  setNewPhoneNumber,
} = userSettingsSlice.actions;

export const selectAll = (state) => state.userSettings;

export const selectCurrentFormId = (state) => state.userSettings.changeFormId;

export const updateEmail = (editAccount) => async (dispatch) => {
  try {
    const response = await axios.put(API_URL + "/e/modify/email", editAccount, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    console.log(response);
  } catch (error) {
    console.log(error);
  }
};

export const updatePhoneNumber = (editAccount) => async (dispatch) => {
  try {
    const response = await axios.put(API_URL + "/e/modify/phone", editAccount, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    console.log(response);
  } catch (error) {
    console.log(error);
  }
};

export const updatePassword = (editAccount) => async (dispatch) => {
  try {
    const response = await axios.put(
      API_URL + "/e/modify/password",
      editAccount,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    );
    console.log(response);
  } catch (error) {
    console.log(error);
  }
};

export default userSettingsSlice.reducer;
