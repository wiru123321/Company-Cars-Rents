import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  reservationBeginDate: "",
  reservationBeginHours: "",
  reservationEndDate: "",
  reservationEndHour: "",
  isChoosen: false,
  choose: true,
  dateIsChoosen: true,
  isCarFormActive: false,
  choosenCar: "",
  stepOne: true,
  stepTwo: false,
  stepThree: false,
  stepFour: false,
  finishForm: false,
  isEndOfForm: false,
  disableBack: true,
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
      state.stepOne = !state.stepOne;
      state.stepTwo = !state.stepTwo;
      state.stepThree = !state.stepTwo;
    },
    firstnameChange: (state, action) => {
      state.firstName = action.payload;
    },
    dateIsChoosenHandler: (state) => {
      state.dateIsChoosen = !state.dateIsChoosen;
    },
    isCarFormActiveHandler: (state) => {
      state.isCarFormActive = !state.isCarFormActive;
    },
    setFinishForm: (state) => {
      state.finishForm = !state.finishForm;
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
    setStepOne: (state) => {
      state.stepOne = !state.stepOne;
    },
    setStepTwo: (state) => {
      state.stepTwo = !state.stepTwo;
    },
    setStepThree: (state) => {
      state.stepThree = !state.stepThree;
    },
    setStepFour: (state) => {
      state.stepFour = !state.stepFour;
    },
    setisChoosen: (state) => {
      state.isChoosen = !state.isChoosen;
    },
    setisEndOfForm: (state) => {
      state.isEndOfForm = !state.isEndOfForm;
    },
    setChoose: (state) => {
      state.choose = !state.choose;
    },
    setdisableBack: (state) => {
      state.disableBack = !state.disableBack;
    },
    setisEndOfFormValue: (state) => {
      state.isEndOfForm = false;
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
  dateIsChoosenHandler,
  isCarFormActiveHandler,
  setStepOne,
  setStepTwo,
  setStepThree,
  setStepFour,
  setFinishForm,
  setisChoosen,
  setChoose,
  setisEndOfForm,
  setdisableBack,
  setisEndOfFormValue,
} = reservationSlice.actions;

export const selectCars = (state) => state.reservation.cars;
export const selectCar = (state) =>
  state.reservation.cars[state.reservation.choosenCar];
export const selectChoose = (state) => state.reservation.choose;
export const selectIsChoosen = (state) => state.reservation.isChoosen;

export const selectbeginDate = (state) =>
  state.reservation.reservationBeginDate;
export const selectbeginHour = (state) =>
  state.reservation.reservationBeginHours;
export const selectendDate = (state) => state.reservation.reservationEndDate;
export const selectendHour = (state) => state.reservation.reservationEndHour;
export const selectFinishForm = (state) => state.reservation.finishForm;
export const selectisEndOfForm = (state) => state.reservation.isEndOfForm;

export const selectDateIsChoosen = (state) => state.reservation.dateIsChoosen;

export const selectStepOne = (state) => state.reservation.stepOne;
export const selectStepTwo = (state) => state.reservation.stepTwo;
export const selectStepThree = (state) => state.reservation.stepThree;
export const selectStepFour = (state) => state.reservation.stepFour;
export const selectchoosenCar = (state) => state.reservation.choosenCar;
export const selectdisableBack = (state) => state.reservation.disableBack;

export const selectIsCarFormActive = (state) =>
  state.reservation.isCarFormActive;

export const fetchCarsAvaiableInDate = (dateFromDateTo, alert) => async (
  dispatch
) => {
  try {
    const response = await axios({
      method: "get",
      url: API_URL + "/e/rent/carsOnTime",
      params: dateFromDateTo,
      headers: { Authorization: "Bearer " + localStorage.getItem("token") },
    });
    console.log(response.data);
    dispatch(setCars(response.data));
    alert.success("Success");
  } catch (error) {
    console.log(error);
    alert.error("Wrong data input, please check entered data.");
  }
};

export const uploadReservCar = (licensePlate, formData, alert) => async (
  dispatch
) => {
  try {
    const response = await axios.post(
      API_URL + `/e/rent/${licensePlate}`,
      formData,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    );
    alert.success("Success");
  } catch (error) {
    console.log(error);
    alert.error("Wrong data input, please check entered data.");
  }
};

export default reservationSlice.reducer;
