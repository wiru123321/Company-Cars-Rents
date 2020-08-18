import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  rents: [],
  currentRent: "",
  enterMenuMode: false,
};

const activeRentsSlice = createSlice({
  name: "activeRents",
  initialState,
  reducers: {
    setRents: (state, action) => {
      state.rents = action.payload;
    },
    setCurrentRent: (state, action) => {
      state.currentRent = action.payload;
    },
    setMenuMode: (state, action) => {
      state.enterMenuMode = action.payload;
    },
  },
});

export const {
  setRents,
  setCurrentRent,
  setMenuMode,
} = activeRentsSlice.actions;

export const selectAll = (state) => state.activeRents;

export const getActiveRents = () => async (dispatch) => {
  try {
    const response = await axios(API_URL + `/a/rent/active_rents`, {
      method: "get",
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });

    dispatch(setCurrentRent(""));
    dispatch(setRents(response.data));
  } catch (err) {
    console.log(err.response);
  }
};

export const fetchCarsBetweenDates = (dateFromDateTo, setCars) => async (
  dispatch
) => {
  try {
    const response = await axios.get(API_URL + "/e/rent/carsOnTime", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
      params: dateFromDateTo,
    });
    setCars(response.data);
    console.log("response", response);
  } catch (err) {
    console.log(err);
  }
};

export const changeRentCar = (id, licensePlate, alert) => async (dispatch) => {
  try {
    const response = await axios(API_URL + `/a/rent/change_car_in_rent/${id}`, {
      method: "put",
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
      params: { licensePlate: licensePlate },
    });
    dispatch(getCar(id));
    alert.success("Car successfully changed!");
  } catch (err) {
    if (err.response) {
      alert.error("Failed to change car! " + err.response.data);
    } else {
      alert.error(
        "Failed to change car due to problems with server connection!"
      );
    }
  }
};

export const getCar = (id) => async (dispatch) => {
  try {
    const response = await axios(API_URL + `/a/rent/${id}`, {
      method: "get",
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    console.log(response);
  } catch (err) {
    console.log(err.response);
  }
};

export default activeRentsSlice.reducer;
