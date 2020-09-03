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
    dispatch(setCars(response.data));
    dispatch(setFilteredCars(response.data));
  } catch (error) {}
};

export const fetchSingleCar = (licensePlate) => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + `/a/car/${licensePlate}`, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(setCurrentCar(response.data));
  } catch (error) {
    console.log(error);
  }
};

export const updateCar = (licensePlate, car, alert, fetchActive) => async (
  dispatch
) => {
  try {
    console.log(car);
    await axios.put(API_URL + `/a/car/${licensePlate}`, car, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(fetchCars(fetchActive));
    alert.success("Updated successfully!");
  } catch (err) {
    alert.error("Failed to update!");
  }
};

export const addFault = (faultDTO, alert, fetchActive) => async (dispatch) => {
  try {
    await axios.post(API_URL + "/a/fault", faultDTO, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });

    dispatch(fetchCars(fetchActive));
    alert.success("Succesfully added issue!");
  } catch (err) {
    if (err.response) {
      if (err.response.status === 409) {
        console.log(err.response);
        alert.error(err.response.data);
      } else {
        alert.error("Failed to add issue!");
      }
    } else {
      alert.error("Failed to add issue! Issues must not repeat!");
    }
  }
};

export const setCarActivity = (
  licensePlate,
  isActive,
  alert,
  fetchActive
) => async (dispatch) => {
  try {
    await axios(API_URL + `/a/car/activity/${licensePlate}`, {
      headers: { Authorization: "Bearer " + localStorage.getItem("token") },
      method: "DELETE",
      params: { isActive: isActive },
    });

    dispatch(fetchSingleCar(licensePlate));
    dispatch(fetchCars(fetchActive));
    alert.success(`Car ${isActive ? "activated" : "suspended"}!`);
  } catch (err) {
    if (err.response) {
      alert.error(
        "Failed to change car activity. Check if car has active rents."
      );
    } else {
      alert.error("Failed to change car activity.");
    }
  }
};

export const deleteCar = (licensePlate, alert, fetchActive) => async (
  dispatch
) => {
  try {
    await axios.delete(API_URL + `/a/car/${licensePlate}`, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(fetchCars(fetchActive));
    dispatch(enterManageCarMode(false));
    dispatch(setCurrentCar(""));
    alert.success("Car successfully deleted!");
  } catch (err) {
    if (err.response) {
      if (err.response.status === 409) {
        alert.error(err.response.data);
      } else {
        alert.error("Failed to delete car!");
      }
    } else {
      alert.error("Failed to delete car! Check connection with cars!");
    }
  }
};

export const uploadPicture = (licensePlate, file, alert, fetchActive) => async (
  dispatch
) => {
  try {
    const formData = new FormData();
    formData.append("imageFile", file);
    const upload = await axios.post(
      API_URL + `/a/car/upload-car-image/${licensePlate}`,
      formData,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
          "content-type": "multipart/form-data",
        },
      }
    );
    dispatch(fetchCars(fetchActive));
    alert.success("Uploaded successfully!");
  } catch (err) {
    alert.error("Failed to upload file.");
  }
};

export const fetchHistory = (licensePlate, setHistory) => async (dispatch) => {
  try {
    const response = await axios.get(
      API_URL + "/a/rent/car_history/" + licensePlate,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    );
    setHistory(response.data);
  } catch (err) {
    console.log(err);
  }
};

export const filterCars = (cars, filterLicensePlate, filterMark) => (
  dispatch
) => {
  let filteredCars = cars.filter((car) =>
    car.licensePlate.toLowerCase().includes(filterLicensePlate.toLowerCase())
  );
  filteredCars = filteredCars.filter((car) =>
    car.modelDTO.markDTO.name.toLowerCase().includes(filterMark.toLowerCase())
  );
  dispatch(setFilteredCars(filteredCars));
};
