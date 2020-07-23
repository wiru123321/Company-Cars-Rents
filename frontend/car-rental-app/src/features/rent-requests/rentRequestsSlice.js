import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  requests: [
    {
      firstname: "Kamil",
      lastname: "Susek",
      beginDate: "20-07-2020",
      beginHour: "20:40",
      endDate: "19-08-2020",
      endHour: "19:20",
    },
    {
      firstname: "Kamil",
      lastname: "Susek",
      beginDate: "20-07-2020",
      beginHour: "20:40",
      endDate: "19-08-2020",
      endHour: "19:20",
    },
    {
      firstname: "Kamil",
      lastname: "Susek",
      beginDate: "20-07-2020",
      beginHour: "20:40",
      endDate: "19-08-2020",
      endHour: "19:20",
    },
  ],
};

export const rentRequestsSlice = createSlice({
  name: "rentRequest",
  initialState,
  reducers: {},
});

export const {} = rentRequestsSlice.actions;

export const selectRequests = (state) => state.rentRequest.requests;

export default rentRequestsSlice.reducer;
