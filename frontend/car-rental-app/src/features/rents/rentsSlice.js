import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  pendingRents: [],
  currentRentIndex: "",
  currentRent: "",
  getAllRents: true,
};

const rentSlice = createSlice({
  name: "rent",
  initialState,
  reducers: {
    setPendingRents: (state, action) => {
      state.pendingRents = action.payload;
    },
    chooseRequest: (state, action) => {
      state.currentRentIndex = action.payload;
      state.currentRent = state.pendingRents[state.currentRentIndex];
    },
    showAll: (state) => {
      state.getAllRents = true;
    },
  },
});

export const { setPendingRents, chooseRequest, showAll } = rentSlice.actions;
export const selectAll = (state) => state.rent;

export const fetchPendingRents = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/a/rent/pending", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    console.log(response.data);
    dispatch(setPendingRents(response.data));
  } catch (error) {}
};

export default rentSlice.reducer;
