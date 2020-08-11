import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  cars: [],
  photoCarIndex: 0,
  parkingNumber: 0,
  parkingPlaceNumber: 0,
  endingFormChoose: false,
  carImg: [],
  chooseCarIndex: 0,
  bugOpen: false,
};
export const yourCarsSlice = createSlice({
  name: "YourReservation",
  initialState,
  reducers: {
    setCars: (state, action) => {
      state.cars = action.payload;
    },
    chooseCar: (state, action) => {
      state.cars[action.payload].isActive = false;
      state.endingFormChoose = !state.endingFormChoose;
      state.chooseCarIndex = action.payload;
    },
    acceptForm: (state) => {
      state.endingFormChoose = !state.endingFormChoose;
    },
    changephotoCarIndex: (state, action) => {
      state.photoCarIndex = action.payload;
    },
    parkingNumberChange: (state, action) => {
      state.parkingNumber = action.payload;
    },
    parkingPlaceNumberChange: (state, action) => {
      state.parkingPlaceNumber = action.payload;
    },
    bugOpenChange: (state) => {
      state.bugOpen = !state.bugOpen;
    },
    bugDescribeChane: (state, action) => {
      state.cars[state.chooseCarIndex].parkingDTO.comment = action.payload;
    },
    setImage: (state, action) => {
      state.carImg = action.payload;
    },
  },
});
export const {
  setCars,
  chooseCar,
  parkingNumberChange,
  parkingPlaceNumberChange,
  acceptForm,
  bugOpenChange,
  bugDescribeChane,
  setImage,
  changephotoCarIndex,
} = yourCarsSlice.actions;

export const selectCars = (state) => state.YourReservation.cars;
export const selectImg = (state) =>
  state.YourReservation.carImg[state.photoCarIndex];
export const selectIndex = (state) => state.YourReservation.chooseCarIndex;
export const selectEndingformchoose = (state) =>
  state.YourReservation.endingFormChoose;
export const selectBugopen = (state) => state.YourReservation.bugOpen;

export const fetchCars = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/ae/active-cars", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });

    dispatch(setCars(response.data));
  } catch (error) {
    console.log(error);
  }
};

export const fetchCarImage = (licensePlate) => async (dispatch) => {
  try {
    console.log("elo");
    const response = await axios.get(
      API_URL + "/a/car/download-car-image/" + licensePlate,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    );
    dispatch(
      setImage(
        "https://images.unsplash.com/photo-1542362567-b07e54358753?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80"
      )
    );
    console.log("elo");
  } catch (error) {
    console.log(error);
  }
};

export default yourCarsSlice.reducer;
