import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  rents: [],
};

const pendingRentsSlice = createSlice({
  name: "pendingRents",
  initialState,
  reducers: {
    setRents: (state, action) => {
      state.rents = action.payload;
    },
  },
});

export const { setRents } = pendingRentsSlice.actions;

export const selectAll = (state) => state.pendingRents;

export default pendingRentsSlice.reducer;

export const fetchPendingEndRents = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/a/rent/end_pending", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(setRents(response.data));
    console.log(response);
  } catch (err) {
    console.log(err);
  }
};

export const acceptPendingRent = (id, alert) => async (dispatch) => {
  try {
    const response = await axios({
      method: "PUT",
      url: API_URL + `/a/rent/permit_end_rent/${id}`,
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(fetchPendingEndRents());
    alert.success("Rent accepted.");
    console.log(response);
  } catch (err) {
    alert.error("Failed to accept rent.");
    console.log(err);
  }
};
