import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { useSelector } from "axios";
import {} from "../../services/LoginService";

const API_URL = "http://localhost:8080";

const json = [
  {
    mark: "Audi",
    model: "RS7 Sportback",
    licensePlate: "SL-1337",
    capacity: "5",
    mileage: "165000",
    year: "1995",
    hp: "245",
    src:
      "https://ocs-pl.oktawave.com/v1/AUTH_2887234e-384a-4873-8bc5-405211db13a2/autoblog/2019/09/Audi-RS7-3.jpg",
  },
  {
    mark: "Fiat",
    model: "Panda",
    licensePlate: "SL-1337",
    capacity: "5",
    mileage: "165000",
    year: "1995",
    hp: "245",
    src:
      "https://www.autocentrum.pl/ac-file/gallery-photo/5dd3eae5583a0f08331eca84/fiat-panda.jpg",
  },
  {
    mark: "Mercedes",
    model: "AMG",
    licensePlate: "SL-1337",
    capacity: "5",
    mileage: "165000",
    year: "1995",
    hp: "245",
    src:
      "https://www.mercedes-benz.pl/passengercars/mercedes-benz-cars/models/a-class/hatchback-w177/amg/comparison-slider/_jcr_content/comparisonslider/par/comparisonslide_576434814/exteriorImage.MQ6.12.20191001221334.jpeg",
  },
];
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

export const fetchCars = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/ae/cars", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(setCars(response.data));
  } catch (error) {
    console.log(error);
  }
};

export const fetchMarks = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/ae/marks", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
  } catch (error) {
    console.log(error);
  }
};

export const deleteCar = (licensePlate) => async (dispatch) => {
  try {
    const response = await axios.delete(API_URL + `/a/car/${licensePlate}`, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
  } catch (error) {
    console.log(error);
  }
};

export default reservationSlice.reducer;
