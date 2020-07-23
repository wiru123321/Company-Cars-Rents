import { configureStore } from "@reduxjs/toolkit";
import reservationReducer from "../features/car-reservation/reservationSlice";
import yourReservationReducer from "../features/your-cars/yourCarsSlice";

export default configureStore({
  reducer: {
    reservation: reservationReducer,
    YourReservation: yourReservationReducer,
  },
});
