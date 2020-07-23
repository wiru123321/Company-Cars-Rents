import React from "react";
import { useSelector, useDispatch } from "react-redux";

import {
  chooseRequest,
  selectChoosenRequestIndex,
} from "../../../features/rent-requests/rentRequestsSlice";

const SingleRequest = () => {
  return <div>single</div>;
};

const AllRequest = () => {
  return <div>all</div>;
};

const RentRequests = () => {
  const requestIndex = useSelector(selectChoosenRequestIndex);
  return requestIndex !== "" ? <SingleRequest /> : <AllRequest />;
};

export default RentRequests;
