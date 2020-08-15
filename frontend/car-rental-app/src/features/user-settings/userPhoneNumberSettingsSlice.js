import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { commonInitialStateProps, commonReducers } from "./CommonProps";

const API_URL = "http://localhost:8080";

const initialState = {
  ...commonInitialStateProps,
  newPhoneNumber: "",
};

const reducers = {
  ...commonReducers,
  setNewPhoneNumber: (state, action) => {
    state.newPhoneNumber = action.payload;
  },
  reset: (state) => {
    state.password = "";
    state.newPhoneNumber = "";
  },
};

export const userPhoneNumberSettingsSlice = createSlice({
  name: "userPhoneNumberSettings",
  initialState,
  reducers,
});

export const {
  setPassword,
  setNewPhoneNumber,
  setResponseMessage,
  setResultStatus,
  toggleSubmit,
  displayResults,
  stopDisplayingResults,
  reset,
} = userPhoneNumberSettingsSlice.actions;

export const selectAll = (state) => state.userPhoneNumberSettings;

export const updatePhoneNumber = (editAccount) => async (dispatch) => {
  try {
    dispatch(toggleSubmit(true));
    const response = await axios.put(API_URL + "/e/modify/phone", editAccount, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(reset());
    dispatch(setResponseMessage("PhoneNumber updated successfully."));
    dispatch(setResultStatus(true));
    dispatch(displayResults());
  } catch (error) {
    if (error.response.status === 406) {
      dispatch(setResponseMessage("Password is invalid."));
      dispatch(setResultStatus(false));
      dispatch(displayResults());
    } else {
      dispatch(setResponseMessage("Could not update phone number."));
      dispatch(setResultStatus(false));
      dispatch(displayResults());
    }
  }
};

export default userPhoneNumberSettingsSlice.reducer;
