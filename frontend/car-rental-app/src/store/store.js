import { configureStore } from "@reduxjs/toolkit";
import reservationReducer from "../features/car-reservation/reservationSlice";
import addEmployeeReducer from "../features/add-employees/addEmployeeSlice";
import rentRequestSlice from "../features/rent-requests/rentRequestsSlice";
import yourReservationReducer from "../features/your-cars/yourCarsSlice";

export default configureStore({
  reducer: {
    reservation: reservationReducer,
    addEmployee: addEmployeeReducer,
    rentRequest: rentRequestSlice,
    YourReservation: yourReservationReducer,
  },
});
