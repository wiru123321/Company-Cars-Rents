import React from "react";
import {
  chooseRequest,
  selectChoosenRequestIndex,
  selectRequests,
  selectCurrentRequest,
} from "../../../features/rent-requests/rentRequestsSlice";
import { useSelector, useDispatch } from "react-redux";
import RequestInfo from "./rentRequestInfo/RequestInfo";
import RentRequestControlPanel from "./rentRequestInfo/RentRequestControlPanel";
import { Container } from "@material-ui/core";
const SingleRequest = () => {
  const request = useSelector(selectCurrentRequest);
  return (
    <Container>
      <RequestInfo request={request} />
      <RentRequestControlPanel />
    </Container>
  );
};

export default SingleRequest;
