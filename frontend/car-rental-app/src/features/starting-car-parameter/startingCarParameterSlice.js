import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  marks: [],
  types: [],
  fuelType: [],
  color: [],
  gearboxType: [],
};

export const startingCarParameterSlice = createSlice({
  name: "startingCarParameter",
  initialState,
  reducers: {
    setMarks: (state, action) => {
      state.marks = action.payload;
    },
    setTypes: (state, action) => {
      state.types = action.payload;
    },
    setFuelType: (state, action) => {
      state.fuelType = action.payload;
    },
    setColor: (state, action) => {
      state.color = action.payload;
    },
    setGearboxType: (state, action) => {
      state.gearboxType = action.payload;
    },
  },
});

export const {
  setMarks,
  setTypes,
  setFuelType,
  setColor,
  setGearboxType,
} = startingCarParameterSlice.actions;
export const selectMarks = (state) => state.startingCarParameter.marks;
export const selectTypes = (state) => state.startingCarParameter.types;
export const selectFuelType = (state) => state.startingCarParameter.fuelType;
export const selectColor = (state) => state.startingCarParameter.color;
export const selectGearboxType = (state) =>
  state.startingCarParameter.gearboxType;

export const fetchMarks = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/a/car-components/marks", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(setMarks(response.data));
  } catch (error) {
    console.log(error);
  }
};

export const fetchTypes = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/a/car-components/types", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(setTypes(response.data));
  } catch (error) {
    console.log(error);
  }
};

export const fetchFuelType = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/a/car-components/fuelTypes", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(setFuelType(response.data));
  } catch (error) {
    console.log(error);
  }
};

export const fetchColor = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/a/car-components/colours", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(setColor(response.data));
  } catch (error) {
    console.log(error);
  }
};

export const fetchGearboxType = () => async (dispatch) => {
  try {
    const response = await axios.get(
      API_URL + "/a/car-components/gearboxTypes",
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    );
    dispatch(setGearboxType(response.data));
  } catch (error) {
    console.log(error);
  }
};

export default startingCarParameterSlice.reducer;
