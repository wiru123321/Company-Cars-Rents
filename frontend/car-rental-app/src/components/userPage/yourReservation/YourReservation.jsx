import React from "react";
import { useSelector } from "react-redux";
import YourCarList from "./YourCarsList";
import EndingReservationForm from "./EndingReservationForm";
import { selectEndingformchoose } from "../../../features/your-cars/yourCarsSlice";

const YourReservation = () => {
  const endingFormChoose = useSelector(selectEndingformchoose);
  if (endingFormChoose) {
    return <EndingReservationForm />;
  } else {
    return <YourCarList />;
  }
};

export default YourReservation;
