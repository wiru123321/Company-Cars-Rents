import { createSlice } from "@reduxjs/toolkit";

let json = [
  {
    mark: "Audi",
    model: "RS7 Sportback",
    licensePlate: "SL-1337",
    capacity: "5",
    mileage: "165000",
    year: "1995",
    hp: "245",
    src:
      "https://ocs-pl.oktawave.com/v1/AUTH_2887234e-384a-4873-8bc5-405211db13a2/autoblog/2019/09/Audi-RS7-3.jpg",
    isEndOfRent: true,
    bugDescribe: "",
  },
  {
    mark: "Fiat",
    model: "Panda",
    licensePlate: "SL-1337",
    capacity: "5",
    mileage: "165000",
    year: "1995",
    hp: "245",
    src:
      "https://www.autocentrum.pl/ac-file/gallery-photo/5dd3eae5583a0f08331eca84/fiat-panda.jpg",
    isEndOfRent: true,
    bugDescribe: "",
  },
  {
    mark: "Mercedes",
    model: "AMG",
    licensePlate: "SL-1337",
    capacity: "5",
    mileage: "165000",
    year: "1995",
    hp: "245",
    src:
      "https://www.mercedes-benz.pl/passengercars/mercedes-benz-cars/models/a-class/hatchback-w177/amg/comparison-slider/_jcr_content/comparisonslider/par/comparisonslide_576434814/exteriorImage.MQ6.12.20191001221334.jpeg",
    isEndOfRent: false,
    bugDescribe: "",
  },
];

const initialState = {
  parkingNumber: 0,
  parkingPlaceNumber: 0,
  endingFormChoose: false,
  isEndOfRent: true,
  chooseCarIndex: 0,
  bugOpen: false,
  cars: json,
};
export const yourCarsSlice = createSlice({
  name: "YourReservation",
  initialState,
  reducers: {
    chooseCar: (state, action) => {
      state.cars[action.payload].isEndOfRent = false;
      state.endingFormChoose = !state.endingFormChoose;
      state.chooseCarIndex = action.payload;
    },
    acceptForm: (state) => {
      state.endingFormChoose = !state.endingFormChoose;
    },
    parkingNumberChange: (state, action) => {
      state.parkingNumber = action.payload;
    },
    parkingPlaceNumberChange: (state, action) => {
      state.parkingPlaceNumber = action.payload;
    },
    bugOpenChange: (state) => {
      state.bugOpen = !state.bugOpen;
    },
    bugDescribeChane: (state, action) => {
      state.cars[state.chooseCarIndex].bugDescribe = action.payload;
    },
  },
});
export const {
  chooseCar,
  parkingNumberChange,
  parkingPlaceNumberChange,
  acceptForm,
  bugOpenChange,
  bugDescribeChane,
} = yourCarsSlice.actions;

export const selectCars = (state) => state.YourReservation.cars;
export const selectEnd = (state) => state.YourReservation.isEndOfRent;
export const selectIndex = (state) => state.YourReservation.chooseCarIndex;
export const selectEndingformchoose = (state) =>
  state.YourReservation.endingFormChoose;
export const selectBugopen = (state) => state.YourReservation.bugOpen;

export default yourCarsSlice.reducer;
