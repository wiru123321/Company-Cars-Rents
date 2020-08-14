import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  cars: [],
  filteredCars: [],
  filterActive: true,
  filterLicensePlate: "",
  filterMark: "",
  currentCar: "",
  manageCarMode: false,
  viewId: 0,
};

const carsManagerSlice = createSlice({
  name: "cars",
  initialState,
  reducers: {
    setCars: (state, action) => {
      state.cars = action.payload;
    },
    setFilteredCars: (state, action) => {
      state.filteredCars = action.payload;
    },
    toggleFilterActive: (state, action) => {
      state.filterActive = action.payload;
    },
    setMarkFilter: (state, action) => {
      state.filterMark = action.payload;
    },
    setLicensePlateFilter: (state, action) => {
      state.filterLicensePlate = action.payload;
    },
    setCurrentCar: (state, action) => {
      state.currentCar = action.payload;
    },
    enterManageCarMode: (state, action) => {
      state.manageCarMode = action.payload;
    },
    setViewId: (state, action) => {
      state.viewId = action.payload;
    },
  },
});

export const selectAll = (state) => state.cars;

export const {
  setCars,
  setMarkFilter,
  toggleFilterActive,
  setLicensePlateFilter,
  setFilteredCars,
  setCurrentCar,
  enterManageCarMode,
  setViewId,
} = carsManagerSlice.actions;

export default carsManagerSlice.reducer;

export const fetchCars = (filterActive) => async (dispatch) => {
  try {
    let link = filterActive ? "/ae/active-cars" : "/a/inactive-cars";
    const response = await axios.get(API_URL + link, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    console.log(response.data);
    dispatch(setCars(response.data));
    dispatch(setFilteredCars(response.data));
  } catch (error) {}
};

export const updateCar = (licensePlate, car, fetchActive) => async (
  dispatch
) => {
  try {
    console.log(car);
    const updateResponse = await axios.put(
      API_URL + `/a/car/${licensePlate}`,
      car,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    );
    console.log(fetchActive);
    dispatch(fetchCars(fetchActive));
  } catch (error) {
    console.log(error);
  }
};

export const addFault = (faultDTO, fetchActive) => async (dispatch) => {
  try {
    const response = await axios.post(API_URL + "/a/fault", faultDTO, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    console.log(fetchActive);
    dispatch(fetchCars(fetchActive));
  } catch (error) {
    console.log(error);
  }
};

export const setCarActivity = (licensePlate, isActive, fetchActive) => async (
  dispatch
) => {
  try {
    const response = await axios(API_URL + `/a/car/activity/${licensePlate}`, {
      headers: { Authorization: "Bearer " + localStorage.getItem("token") },
      method: "DELETE",
      params: { isActive: isActive },
    });

    dispatch(fetchCars(fetchActive));
  } catch (error) {
    console.log(error);
  }
};

export const deleteCar = (licensePlate, fetchActive) => async (dispatch) => {
  try {
    const deleteResponse = await axios.delete(
      API_URL + `/a/car/${licensePlate}`,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    );
    dispatch(fetchCars(fetchActive));
    dispatch(enterManageCarMode(false));
    dispatch(setCurrentCar(""));
  } catch (error) {
    console.log(error);
  }
};

export const filterCars = (cars, filterLicensePlate, filterMarks) => (
  dispatch
) => {
  let filteredCars = cars.filter((car) =>
    car.licensePlate.includes(filterLicensePlate)
  );
  filteredCars = filteredCars.filter((car) =>
    car.modelDTO.markDTO.name.includes(filterMarks)
  );
  dispatch(setFilteredCars(filteredCars));
};
