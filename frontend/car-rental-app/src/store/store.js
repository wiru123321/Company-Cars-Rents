import { configureStore } from "@reduxjs/toolkit";
import reservationReducer from "../features/car-reservation/reservationSlice";
import addEmployeeReducer from "../features/add-employees/addEmployeeSlice";
import rentRequestSlice from "../features/rent-requests/rentRequestsSlice";
import yourReservationReducer from "../features/your-cars/yourCarsSlice";
import carsInfoReducer from "../features/add-car-info/carsInfoSlice";
import authReducer from "../features/authentication/authSlice";
import startingCarParameterReducer from "../features/starting-car-parameter/startingCarParameterSlice";
import userSettingsReducer from "../features/user-settings/userSettingsSlice";

export default configureStore({
  reducer: {
    reservation: reservationReducer,
    addEmployee: addEmployeeReducer,
    rentRequest: rentRequestSlice,
    auth: authReducer,
    YourReservation: yourReservationReducer,
    carsInfo: carsInfoReducer,
    startingCarParameter: startingCarParameterReducer,
    userSettings: userSettingsReducer,
  },
});
