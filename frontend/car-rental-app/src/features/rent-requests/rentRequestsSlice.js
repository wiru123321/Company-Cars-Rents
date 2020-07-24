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
      description:
        "DescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescription",
    },
    {
      firstname: "Kamil",
      lastname: "Susek",
      beginDate: "20-07-2020",
      beginHour: "20:40",
      endDate: "19-08-2020",
      endHour: "19:20",
      description:
        "DescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescription",
    },
    {
      firstname: "Kamil",
      lastname: "Susek",
      beginDate: "20-07-2020",
      beginHour: "20:40",
      endDate: "19-08-2020",
      endHour: "19:20",
      description:
        "DescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescription",
    },
  ],

  choosenRequestIndex: "",
};

export const rentRequestsSlice = createSlice({
  name: "rentRequest",
  initialState,
  reducers: {
    chooseRequest: (state, action) => {
      state.choosenRequestIndex = action.payload;
    },
  },
});

export const { chooseRequest } = rentRequestsSlice.actions;

export const selectRequests = (state) => state.rentRequest.requests;

export const selectChoosenRequestIndex = (state) =>
  state.rentRequest.choosenRequestIndex;

export const selectCurrentRequest = (state) =>
  state.rentRequest.requests[state.rentRequest.choosenRequestIndex];

export default rentRequestsSlice.reducer;
