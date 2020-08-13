import React from "react";
import { useSelector } from "react-redux";
import { selectAll } from "../../../features/rents/rentsSlice";
import SingleRequest from "./SingleRequest";
import AllRequests from "./AllRequests";

const RentRequests = () => {
  const { currentRentIndex } = useSelector(selectAll);
  return currentRentIndex !== "" ? <SingleRequest /> : <AllRequests />;
};

export default RentRequests;
