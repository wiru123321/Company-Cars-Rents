import React from "react";
import { useSelector } from "react-redux";
import { selectChoosenRequestIndex } from "../../../features/rent-requests/rentRequestsSlice";
import SingleRequest from "./SingleRequest";
import AllRequests from "./AllRequests";

const RentRequests = () => {
  const requestIndex = useSelector(selectChoosenRequestIndex);
  return requestIndex !== "" ? <SingleRequest /> : <AllRequests />;
};

export default RentRequests;
