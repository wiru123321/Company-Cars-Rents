import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  requestDate: "",
  firstName: "",
  lastName: "",
  reservationBeginDate: "",
  reservationBeginHours: "",
  reservationEndDate: "",
  reservationEndHour: "",
};

export const reservationSlice = createSlice({
  name: "reservation",
  initialState,
  reducers: {
    setState: (state, action) => {},
  },
});

export default reservationSlice.reducer;
