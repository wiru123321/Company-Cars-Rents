import React from "react";
import {
  chooseRequest,
  selectChoosenRequestIndex,
  selectRequests,
  selectCurrentRequest,
} from "../../../features/rent-requests/rentRequestsSlice";
import { useSelector, useDispatch } from "react-redux";
import RequestInfo from "./rentRequestInfo/RequestInfo";

const SingleRequest = () => {
  const {
    firstname,
    lastname,
    beginDate,
    beginHour,
    endDate,
    endHour,
  } = useSelector(selectCurrentRequest);
  return (
    <div>
      {firstname} {lastname} {beginDate}
      {beginHour} {endDate} {endHour}
    </div>
  );
};

export default SingleRequest;
