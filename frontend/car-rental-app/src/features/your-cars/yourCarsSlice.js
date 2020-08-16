import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  reservation: [],
  historyReservation: [],
  requestReservation: [],
  photoCarIndex: 0,
  endingFormChoose: false,
  carImg: [],
  chooseCarIndex: 0,
  bugOpen: false,
};
export const yourCarsSlice = createSlice({
  name: "YourReservation",
  initialState,
  reducers: {
    setReservation: (state, action) => {
      state.reservation = action.payload;
    },
    setHistoryReservation: (state, action) => {
      state.historyReservation = action.payload;
    },
    setRequestReservation: (state, action) => {
      state.requestReservation = action.payload;
    },
    chooseCar: (state, action) => {
      state.endingFormChoose = !state.endingFormChoose;
      state.chooseCarIndex = action.payload;
    },
    acceptForm: (state) => {
      state.endingFormChoose = !state.endingFormChoose;
    },
    bugOpenChange: (state) => {
      state.bugOpen = !state.bugOpen;
    },
    bugDescribeChane: (state, action) => {
      state.reservation.carDTO[state.chooseCarIndex].parkingDTO.comment =
        action.payload;
    },
    setImage: (state, action) => {
      state.carImg = action.payload;
    },
  },
});
export const {
  setReservation,
  chooseCar,
  acceptForm,
  bugOpenChange,
  bugDescribeChane,
  setImage,
  setHistoryReservation,
  setRequestReservation,
} = yourCarsSlice.actions;

export const selectReservation = (state) => state.YourReservation.reservation;
export const selectHistoryReservation = (state) =>
  state.YourReservation.historyReservation;
export const selectRequestReservation = (state) =>
  state.YourReservation.requestReservation;
export const selectImg = (state) =>
  state.YourReservation.carImg[state.photoCarIndex];
export const selectIndex = (state) => state.YourReservation.chooseCarIndex;
export const selectEndingformchoose = (state) =>
  state.YourReservation.endingFormChoose;
export const selectBugopen = (state) => state.YourReservation.bugOpen;

export const fetchReservation = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/e/rent/my_rents", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    console.log(response.data);
    dispatch(setReservation(response.data));
  } catch (error) {
    console.log(error);
  }
};

export const fetchHistoryReservation = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/e/rent/my_history", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(setHistoryReservation(response.data));
  } catch (error) {
    console.log(error);
  }
};

export const fetchRequestReservation = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/e/rent/my_requests", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(setRequestReservation(response.data));
  } catch (error) {
    console.log(error);
  }
};

export const fetchCarImage = (licensePlate) => async (dispatch) => {
  try {
    const response = await axios.get(
      API_URL + "/e/car/download-car-image/" + licensePlate,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
        responseType: "arraybuffer",
      }
    );
    // dispatch(setImage(response.data));
    console.log(response.data);
    var arrayBufferView = new Uint8Array(response.data);
    var blob = new Blob([arrayBufferView], { type: "image" });
    var urlCreator = window.URL || window.webkitURL;
    var imageUrl = urlCreator.createObjectURL(blob);
    console.log(imageUrl);
    dispatch(setImage(imageUrl));
  } catch (error) {
    console.log(error);
  }
};

export const backTheCarBack = (id, parkingDTO) => async (dispatch) => {
  try {
    const response = await axios({
      method: "delete",
      url: API_URL + `/e/rent/end_rent/${id}`,
      data: parkingDTO,
      headers: { Authorization: "Bearer " + localStorage.getItem("token") },
    });
  } catch (error) {
    console.log(error);
  }
};
//TODO backend poprawic ma dostęp do tej metody.
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
    dispatch(setReservation(fetchResponse.data));
  } catch (error) {
    console.log(error);
  }
};

export default yourCarsSlice.reducer;
