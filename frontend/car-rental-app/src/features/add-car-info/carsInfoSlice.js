import { createSlice } from "@reduxjs/toolkit";

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

export const selectBrandValue = (state) => state.brand;
export const selectTypeValue = (state) => state.type;
export const selectLicencePlate = (state) => state.licencePlate;
export const selectFuelTypeValue = (state) => state.fuelType;
export const selectYear = (state) => state.year;
export const selectMilage = (state) => state.milage;
export const selectHp = (state) => state.hp;
export const selectPeopleCapacity = (state) => state.peopleCapacity;
export const selectDoorsNumber = (state) => state.doorsNumber;
export const selectColorValue = (state) => state.color;
export const selectGearboxTypeValue = (state) => state.gearboxType;
export const selectTrunkCapacity = (state) => state.trunkCapacity;
export const selectImageUrl = (state) => state.imageUrl;

export default carsInfoSlice.reducer;
