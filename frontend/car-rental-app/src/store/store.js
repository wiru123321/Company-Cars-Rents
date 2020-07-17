import { configureStore } from "@reduxjs/toolkit";
import reservationReducer from "../features/car-reservation/reservationSlice";

export default configureStore({
  reducer: {
    reservation: reservationReducer,
  },
});
