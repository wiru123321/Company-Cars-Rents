import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  marks: [],
};

export const startingCarParameterSlice = createSlice({
  name: "startingCarParameter",
  initialState,
  reducers: {
    setMarks: (state, action) => {
      state.marks = action.payload;
      console.log(state.marks);
    },
  },
});

export const { setMarks } = startingCarParameterSlice.actions;
export const selectMarks = (state) => state.startingCarParameter.marks;

export const fetchMarks = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/a/car-components/marks", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    console.log(response.data);
    dispatch(setMarks(response.data));
  } catch (error) {
    console.log(error);
  }
};

export default startingCarParameterSlice.reducer;
