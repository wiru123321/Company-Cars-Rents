import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

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
};

export const carsInfoSlice = createSlice({
  name: "carsInfo",
  initialState,
  reducers: {
    brandChange: (state, action) => {
      state.brand = action.payload;
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
    reset: (state) => {
      state.brand = " ";
      state.type = " ";
      state.licencePlate = " ";
      state.fuelType = "";
      state.year = "";
      state.milage = "";
      state.hp = "";
      state.peopleCapacity = "";
      state.doorsNumber = "";
      state.color = "";
      state.gearboxType = "";
      state.trunkCapacity = "";
      state.imageUrl = "";
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
  reset,
} = carsInfoSlice.actions;

export const selectAll = (state) => state.carsInfo;

export const selectBrandValue = (state) => state.carsInfo.brand;

export const selectTypeValue = (state) => state.carsInfo.type;

export const selectLicencePlate = (state) => state.carsInfo.licencePlate;

export const selectFuelTypeValue = (state) => state.carsInfo.fuelType;

export const selectYear = (state) => state.carsInfo.year;

export const selectMilage = (state) => state.carsInfo.milage;

export const selectHp = (state) => state.carsInfo.hp;

export const selectPeopleCapacity = (state) => state.carsInfo.peopleCapacity;

export const selectDoorsNumber = (state) => state.carsInfo.doorsNumber;

export const selectColorValue = (state) => state.carsInfo.color;

export const selectGearboxTypeValue = (state) => state.carsInfo.gearboxType;

export const selectTrunkCapacity = (state) => state.carsInfo.trunkCapacity;

export const selectImageUrl = (state) => state.carsInfo.imageUrl;

export const addCar = (carCration) => async (dispatch) => {
  try {
    const response = await axios.post(API_URL + "/a/car", carCration, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
  } catch (error) {
    console.log(error);
  }
};

export default carsInfoSlice.reducer;
