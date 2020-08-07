import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  modelDTO: "",
  typeDTO: "",
  licensePlate: "",
  fuelTypeDTO: "",
  productionYear: "",
  mileage: "",
  enginePower: "",
  capacityOfPeople: "",
  doorsNumber: "",
  colourDTO: "",
  gearBoxTypeDTO: "",
  capacityOfTrunkScale: "",
  photoInFolderName: "",
  lastInspection: "",
  isActive: false,
  lastInspection: "",
  parkingDTO: "",
  imageFile: "",
  parkingDTO: "",
  town: "",
  postalCode: "",
  streetName: "",
  number: "",
  comment: "",
};

export const carsInfoSlice = createSlice({
  name: "carsInfo",
  initialState,
  reducers: {
    brandChange: (state, action) => {
      state.modelDTO = action.payload;
    },
    typeChange: (state, action) => {
      state.typeDTO = action.payload;
    },
    licencePlateChange: (state, action) => {
      state.licensePlate = action.payload;
    },
    fuelTypeChange: (state, action) => {
      state.fuelTypeDTO = action.payload;
    },
    yearChange: (state, action) => {
      state.productionYear = action.payload;
    },
    milageChange: (state, action) => {
      state.mileage = action.payload;
    },
    hpChange: (state, action) => {
      state.enginePower = action.payload;
    },
    peopleCapacityChange: (state, action) => {
      state.capacityOfPeople = action.payload;
    },
    doorsNumberChange: (state, action) => {
      state.doorsNumber = action.payload;
    },
    colorChange: (state, action) => {
      state.colourDTO = action.payload;
    },
    gearboxTypeChange: (state, action) => {
      state.gearBoxTypeDTO = action.payload;
    },
    trunkCapacityChange: (state, action) => {
      state.capacityOfTrunkScale = action.payload;
    },
    townChange: (state, action) => {
      state.capacityOfTrunkScale = action.payload;
    },
    postalCodeChange: (state, action) => {
      state.capacityOfTrunkScale = action.payload;
    },
    streetNameChange: (state, action) => {
      state.capacityOfTrunkScale = action.payload;
    },
    numberChange: (state, action) => {
      state.capacityOfTrunkScale = action.payload;
    },
    commentChange: (state, action) => {
      state.capacityOfTrunkScale = action.payload;
    },
    lastInspectionChange: (state, action) => {
      state.capacityOfTrunkScale = action.payload;
    },
    reset: (state) => {
      state.modelDTO = " ";
      state.typeDTO = " ";
      state.licensePlate = " ";
      state.fuelTypeDTO = "";
      state.productionYear = "";
      state.mileage = "";
      state.enginePower = "";
      state.capacityOfPeople = "";
      state.doorsNumber = "";
      state.colourDTO = "";
      state.gearBoxTypeDTO = "";
      state.capacityOfTrunkScale = "";
      state.photoInFolderName = "";
    },
    imageFileChange: (state, action) => {
      state.imageFile = action.payload;
      console.log(state.imageFile);
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
  imageFileChange,
  lastInspectionChange,
  commentChange,
  townChange,
  postalCodeChange,
  streetNameChange,
  numberChange,
} = carsInfoSlice.actions;

export const selectAll = (state) => state.carsInfo;

export const addCar = (carDTO) => async (dispatch) => {
  try {
    const response = await axios.post(API_URL + "/a/car", carDTO, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
  } catch (error) {
    console.log(error);
  }
};

export const addImage = (file, licensePlate) => async (dispatch) => {
  try {
    const json = JSON.stringify(file);
    const blob = new Blob([json], {
      type: "application/json",
    });
    const data = new FormData();
    data.append("imageFile", blob);
    const response = await axios.post(
      API_URL + "/file/upload-car-image/" + licensePlate,
      data,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    );
  } catch (error) {
    console.log(error);
  }
};

export default carsInfoSlice.reducer;
