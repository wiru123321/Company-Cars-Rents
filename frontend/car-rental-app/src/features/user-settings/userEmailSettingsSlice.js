import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { commonInitialStateProps, commonReducers } from "./CommonProps";

const API_URL = "http://localhost:8080";

const initialState = {
  ...commonInitialStateProps,
  newEmail: "",
};

const reducers = {
  ...commonReducers,
  setNewEmail: (state, action) => {
    state.newEmail = action.payload;
  },
  reset: (state) => {
    state.password = "";
    state.newEmail = "";
  },
};

export const userEmailSettingsSlice = createSlice({
  name: "userEmailSettings",
  initialState,
  reducers,
});

export const {
  setPassword,
  setNewEmail,
  setResponseMessage,
  setResultStatus,
  toggleSubmit,
  displayResults,
  stopDisplayingResults,
  reset,
} = userEmailSettingsSlice.actions;

export const selectAll = (state) => state.userEmailSettings;

export const updateEmail = (editAccount) => async (dispatch) => {
  try {
    dispatch(toggleSubmit(true));
    const response = await axios.put(API_URL + "/e/modify/email", editAccount, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(reset());
    dispatch(setResponseMessage("Email updated successfully."));
    dispatch(setResultStatus(true));
    dispatch(displayResults());
  } catch (error) {
    if (error.response.status === 406) {
      dispatch(setResponseMessage("Password is invalid."));
      dispatch(setResultStatus(false));
      dispatch(displayResults());
    } else {
      dispatch(setResponseMessage("Could not update email."));
      dispatch(setResultStatus(false));
      dispatch(displayResults());
    }
  }
};

export default userEmailSettingsSlice.reducer;
