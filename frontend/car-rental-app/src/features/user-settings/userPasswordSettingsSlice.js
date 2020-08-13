import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { commonInitialStateProps, commonReducers } from "./CommonProps";

const API_URL = "http://localhost:8080";

const initialState = {
  ...commonInitialStateProps,
  newPassword: "",
};

const reducers = {
  ...commonReducers,
  setNewPassword: (state, action) => {
    state.newPassword = action.payload;
  },
  reset: (state) => {
    state.password = "";
    state.newPassword = "";
  },
};

export const userPasswordSettingsSlice = createSlice({
  name: "userPasswordSettings",
  initialState,
  reducers,
});

export const {
  setPassword,
  setNewPassword,
  setResponseMessage,
  setResultStatus,
  toggleSubmit,
  displayResults,
  stopDisplayingResults,
  reset,
} = userPasswordSettingsSlice.actions;

export const selectAll = (state) => state.userPasswordSettings;

export const updatePassword = (editAccount) => async (dispatch) => {
  try {
    dispatch(toggleSubmit(true));
    const response = await axios.put(
      API_URL + "/e/modify/password",
      editAccount,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    );
    dispatch(reset());
    dispatch(setResponseMessage("Password updated successfully."));
    dispatch(setResultStatus(true));
    dispatch(displayResults());
  } catch (error) {
    if (error.response.status === 406) {
      dispatch(setResponseMessage("Password is invalid."));
      dispatch(setResultStatus(false));
      dispatch(displayResults());
    } else {
      dispatch(setResponseMessage("Could not update password."));
      dispatch(setResultStatus(false));
      dispatch(displayResults());
    }
  }
};

export default userPasswordSettingsSlice.reducer;
