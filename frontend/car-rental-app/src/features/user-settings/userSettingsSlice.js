import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  changeFormId: 0,
  password: "",
  newPassword: "",
  newEmail: "",
  newPhoneNumber: "",
  successMessage: "",
  errorMessage: "",
  showSubmitInfo: false,
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
    setSubmitResult: (state, action) => {
      state.showSubmitInfo = action.payload;
    },
    setSuccessMessage: (state, action) => {
      state.successMessage = action.payload;
    },
    setErrorMessage: (state, action) => {
      state.errorMessage = action.payload;
    },
    resetInputFields: (state) => {
      state.password = "";
      state.newPassword = "";
      state.newEmail = "";
      state.newPhoneNumber = "";
    },
    resetErrorMessage: (state) => {
      state.successMessage = "";
      state.errorMessage = "";
      state.showSubmitInfo = false;
    },
  },
});

export const {
  toggleForm,
  setPassword,
  setNewPassword,
  setNewEmail,
  setNewPhoneNumber,
  resetFields,
  resetErrorMessage,
  setSuccessMessage,
  setErrorMessage,
  setSubmitResult,
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
    dispatch(setSubmitResult(true));
    dispatch(setSuccessMessage("Email added successfully."));
  } catch (error) {
    if (error.response.status === 406) {
      dispatch(setSubmitResult(false));
      dispatch(setErrorMessage("Password is invalid."));
    } else {
      dispatch(setSubmitResult(false));
      dispatch(setErrorMessage("Failed to update email."));
    }
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
    if (error.code === 406) console.log("bad password");
    else console.log(JSON.stringify(error));
  }
};

export default userSettingsSlice.reducer;
