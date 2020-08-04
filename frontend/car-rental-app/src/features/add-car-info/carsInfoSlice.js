import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080/";

const initialState = {
  brand: "",
  type: "",
  licencePlate: "",
  fuelType: "",
  year: "",
  milage: "",
  hp: "",
  peopleCapacity: "",
  doorsNumber: "",
  color: "",
  gearboxType: "",
  trunkCapacity: "",
  imageUrl: "",
  marks: [],
};

export const carsInfoSlice = createSlice({
  name: "carsInfo",
  initialState,
  reducers: {
    brandChange: (state, action) => {
      state.brand = action.payload;
      console.log(state.marks);
    },
    typeChange: (state, action) => {
      state.type = action.payload;
    },
    licencePlateChange: (state, action) => {
      state.licencePlate = action.payload;
    },
    fuelTypeChange: (state, action) => {
      state.fuelType = action.payload;
    },
    yearChange: (state, action) => {
      state.year = action.payload;
      console.log(state.year);
    },
    milageChange: (state, action) => {
      state.milage = action.payload;
    },
    hpChange: (state, action) => {
      state.hp = action.payload;
    },
    peopleCapacityChange: (state, action) => {
      state.peopleCapacity = action.payload;
    },
    doorsNumberChange: (state, action) => {
      state.doorsNumber = action.payload;
    },
    colorChange: (state, action) => {
      state.color = action.payload;
    },
    gearboxTypeChange: (state, action) => {
      state.gearboxType = action.payload;
    },
    trunkCapacityChange: (state, action) => {
      state.trunkCapacity = action.payload;
    },
    imageUrlChange: (state, action) => {
      state.imageUrl = action.payload;
    },
    marksLoader: (state, action) => {
      console.log("in");
      return { marks: [...state.marks, ...action.payload] };
    },
  },
});

export const {
  brandChange,
  typeChange,
  licencePlateChange,
  fuelTypeChange,
  yearChange,
  milageChange,
  hpChange,
  peopleCapacityChange,
  doorsNumberChange,
  colorChange,
  gearboxTypeChange,
  trunkCapacityChange,
  imageUrlChange,
  marksLoader,
} = carsInfoSlice.actions;

export const fetchMarks = async (dispatch, state) => {
  try {
    const response = await axios.get(API_URL + "a/car-components/marks", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    console.log(response.data);
  } catch (error) {
    console.log(error);
  }
};

export default carsInfoSlice.reducer;
