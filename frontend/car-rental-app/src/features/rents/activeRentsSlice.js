import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  rents: [],
  filteredRents: [],
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
    setFilteredRents: (state, action) => {
      state.filteredRents = action.payload;
    },
  },
});

export const {
  setRents,
  setCurrentRent,
  setMenuMode,
  setFilteredRents,
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
    dispatch(getRentByID(id));
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

export const getRentByID = (id) => async (dispatch) => {
  try {
    const response = await axios(API_URL + `/a/rent/${id}`, {
      method: "get",
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(setCurrentRent(response.data));
    console.log("new rent", response.data);
  } catch (err) {
    console.log(err.response);
  }
};

export const filterRents = (rents, filters) => (dispatch) => {
  const { name, surname, mark, model, licensePlate } = filters;

  let filteredRents = rents.filter((rent) =>
    filteredRents.userRentInfo.name.toLowerCase().includes(name.toLowerCase())
  );

  filteredRents = filteredRents.filter((rent) =>
    filteredRents.userRentInfo.surname
      .toLowerCase()
      .includes(surname.toLowerCase())
  );

  filteredRents = filteredRents.filter((rent) =>
    filteredRents.carDTO.modelDTO.markDTO.name
      .toLowerCase()
      .includes(mark.toLowerCase())
  );

  filteredRents = filteredRents.filter((rent) =>
    filteredRents.carDTO.modelDTO.name
      .toLowerCase()
      .includes(model.toLowerCase())
  );

  filteredRents = filteredRents.filter((rent) =>
    filteredRents.carDTO.licensePlate
      .toLowerCase()
      .includes(licensePlate.toLowerCase())
  );

  dispatch(setFilteredRents(filteredRents));
};

export default activeRentsSlice.reducer;
