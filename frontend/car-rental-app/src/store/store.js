import { configureStore } from "@reduxjs/toolkit";
import reservationReducer from "../features/car-reservation/reservationSlice";
import addEmployeeReducer from "../features/car-reservation/addEmployeeSlice";
import rentRequestSlice from "../features/rent-requests/rentRequestsSlice";

export default configureStore({
  reducer: {
    reservation: reservationReducer,
    addEmployee: addEmployeeReducer,
    rentRequest: rentRequestSlice,
  },
});
