import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  firstName: "",
  lastName: "",
  reservationBeginDate: "",
  reservationBeginHours: "",
  reservationEndDate: "",
  reservationEndHour: "",
  isChoosen: false,
  choose: false,
  choosenCar: 0,
  cars: [],
};

export const reservationSlice = createSlice({
  name: "reservation",
  initialState,
  reducers: {
    chooseCar: (state, action) => {
      state.isChoosen = true;
      state.choosenCar = action.payload;
    },

    undoChoose: (state) => {
      state.isChoosen = false;
      state.choosenCar = 0;
    },

    toggleChoose: (state) => {
      state.choose = !state.choose;
    },
    firstnameChange: (state, action) => {
      state.firstName = action.payload;
    },
    lastnameChange: (state, action) => {
      state.lastName = action.payload;
    },
    beginDateChange: (state, action) => {
      state.reservationBeginDate = action.payload;
    },
    beginHourChange: (state, action) => {
      state.reservationBeginHours = action.payload;
    },
    endDateChange: (state, action) => {
      state.reservationEndDate = action.payload;
    },
    endHourChange: (state, action) => {
      state.reservationEndHour = action.payload;
    },
    setCars: (state, action) => {
      state.cars = action.payload;
    },
  },
});

export const {
  chooseCar,
  undoChoose,
  toggleChoose,
  firstnameChange,
  lastnameChange,
  beginDateChange,
  beginHourChange,
  endDateChange,
  endHourChange,
  setCars,
  getCars,
} = reservationSlice.actions;

export const selectCars = (state) => state.reservation.cars;
export const selectCar = (state) =>
  state.reservation.cars[state.reservation.choosenCar];
export const selectChoose = (state) => state.reservation.choose;
export const selectIsChoosen = (state) => state.reservation.isChoosen;

export const fetchActiveCars = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/ae/active-cars", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    console.log(response.data);
    dispatch(setCars(response.data));
  } catch (error) {
    console.log(error);
  }
};

export default reservationSlice.reducer;
