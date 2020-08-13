import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  pendingRents: [],
  currentRentIndex: "",
  currentRent: "",
  getAllRents: true,
  response: "",
  didUpdate: false,
  didUpdateSuccess: false,
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
    setResponse: (state, action) => {
      state.response = action.payload;
    },
    setUpdateResult: (state, action) => {
      state.didUpdate = true;
      state.didUpdateSuccess = action.payload;
    },
    resetUpdate: (state) => {
      state.didUpdate = false;
      state.didUpdateSuccess = false;
    },
  },
});

export const {
  setPendingRents,
  chooseRequest,
  showAll,
  setResponse,
  setUpdateResult,
  resetUpdate,
} = rentSlice.actions;
export const selectAll = (state) => state.rent;

export const acceptRentRequest = (rentId, rentPermitRejectDTO) => async (
  dispatch
) => {
  try {
    const response = await axios.put(
      API_URL + `/a/rent/permit/${rentId}`,
      rentPermitRejectDTO,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    );

    dispatch(chooseRequest(""));
    dispatch(fetchPendingRents());
    dispatch(setUpdateResult(true));
  } catch (error) {
    dispatch(setUpdateResult(false));
  }
};

export const rejectRentRequest = (rentId, rentPermitRejectDTO) => async (
  dispatch
) => {
  try {
    const response = await axios.delete(
      API_URL + `/a/rent/reject/${rentId}`,
      rentPermitRejectDTO,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    );
    dispatch(chooseRequest(""));
    dispatch(fetchPendingRents());
    dispatch(setUpdateResult(true));
  } catch (error) {
    dispatch(setUpdateResult(false));
  }
};

export const fetchPendingRents = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/a/rent/pending", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    console.log(response.data);
    dispatch(setPendingRents(response.data));
  } catch (error) {
    console.log(error);
  }
};

export default rentSlice.reducer;
