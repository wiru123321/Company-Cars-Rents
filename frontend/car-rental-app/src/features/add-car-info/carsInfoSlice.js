import { createSlice } from "@reduxjs/toolkit";

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
  name: "CarsInfo",
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
} = carsInfoSlice.actions;

export default carsInfoSlice.reducer;
