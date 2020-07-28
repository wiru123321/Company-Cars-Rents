import { configureStore } from "@reduxjs/toolkit";
import reservationReducer from "../features/car-reservation/reservationSlice";
import addEmployeeReducer from "../features/add-employees/addEmployeeSlice";
import rentRequestSlice from "../features/rent-requests/rentRequestsSlice";
import yourReservationReducer from "../features/your-cars/yourCarsSlice";
import carsInfoReducer from "../features/add-car-info/carsInfoSlice";

export default configureStore({
  reducer: {
    reservation: reservationReducer,
    addEmployee: addEmployeeReducer,
    rentRequest: rentRequestSlice,
    YourReservation: yourReservationReducer,
    CarsInfo: carsInfoReducer,
  },
});
