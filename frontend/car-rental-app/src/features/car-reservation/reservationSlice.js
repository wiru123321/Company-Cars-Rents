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
/*
filteredCars: [],
filterLicensePlate: "",
filterMark: "",
filterActive: true,
setLicenseFilters: (state, action) => {
  state.filterLicensePlate = action.payload;
},
setMarkFilters: (state, action) => {
  state.filterMark = action.payload;
},
setActiveFilters: (state, action) => {
  state.filterActive = action.payload;
},
setFilteredCars: (state, action) => {
  state.filteredCars = action.payload;
},
  setLicenseFilters,
  setMarkFilters,
  setFilteredCars,*/
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
    const response = await axios.get(API_URL + "/a/cars", {
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

export const fetchInactiveCars = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/a/active-cars", {
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
    const deleteResponse = await axios.delete(
      API_URL + `/a/car/${licensePlate}`,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    );
    const fetchResponse = await axios.get(API_URL + "/a/cars", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    console.log(fetchResponse.data);
    dispatch(setCars(fetchResponse.data));
  } catch (error) {
    console.log(error);
  }
};

export const updateCar = (licensePlate, car) => async (dispatch) => {
  try {
    const updateResponse = await axios.put(
      API_URL + `/a/car/${licensePlate}`,
      car,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    );
    const fetchResponse = await axios.get(API_URL + "/a/cars", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    console.log("onUpdate", fetchResponse.data);
    dispatch(setCars(fetchResponse.data));
  } catch (error) {
    console.log(error);
  }
};

export default reservationSlice.reducer;
