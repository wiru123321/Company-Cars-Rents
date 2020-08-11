import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  cars: [],
  filteredCars: [],
  filterLicensePlate: "",
  filterMark: "",
  didUpdate: false,
  didUpdateSuccess: false,
};

const carManagerSlice = createSlice({
  name: "carsManager",
  initialState,
  reducers: {
    setCars: (state, action) => {
      state.cars = action.payload;
    },
    setFilteredCars: (state, action) => {
      state.filteredCars = action.payload;
    },
    setLicenseFilters: (state, action) => {
      state.filterLicensePlate = action.payload;
    },
    setMarkFilters: (state, action) => {
      state.filterMark = action.payload;
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
  setCars,
  setFilteredCars,
  setLicenseFilters,
  setMarkFilters,
  setUpdateResult,
  resetUpdate,
} = carManagerSlice.actions;

export const selectCars = (state) => state.carsManager.cars;
export const selectAll = (state) => state.carsManager;

export const fetchCars = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/a/cars", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });

    dispatch(setCars(response.data));
    dispatch(setFilteredCars(response.data));
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
    const response = await axios.get(API_URL + "/a/inactive-cars", {
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
    dispatch(setCars(fetchResponse.data));
    dispatch(setUpdateResult(true));
  } catch (error) {
    dispatch(setUpdateResult(false));
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

export default carManagerSlice.reducer;
